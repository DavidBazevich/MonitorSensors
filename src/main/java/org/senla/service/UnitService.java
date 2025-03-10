package org.senla.service;

import lombok.RequiredArgsConstructor;
import org.senla.dto.creators.UnitCreateDto;
import org.senla.dto.UnitDto;
import org.senla.dto.mapper.UnitMapper;
import org.senla.entity.Units;
import org.senla.exception.ResourceNotFoundException;
import org.senla.repository.UnitRepository;
import org.senla.service.Impl.UnitServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UnitService implements UnitServiceImp {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;


    @Override
    public List<UnitDto> findAllUnits() {
        return unitRepository.findAll()
                .stream()
                .map(unitMapper::toUnitDto)
                .toList();
    }

    @Override
    @Transactional
    public UnitDto saveUnit(UnitCreateDto unit) {
        return Optional.of(createType(unit))
                .map(unitRepository::save)
                .map(unitMapper::toUnitDto)
                .orElseThrow(() -> new ResourceNotFoundException("Incorrect data"));
    }

    @Override
    @Transactional
    public UnitDto updateUnit(String name, UnitCreateDto newUnit){ //TODO update
        Units unit = unitRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with name: " + name));
        unit.setName(newUnit.getName());
        unitRepository.save(unit);
        return unitMapper.toUnitDto(unit);
    }

    @Override
    public UnitDto findById(Integer id) {
        return unitRepository.findById(id)
                .map(unitMapper::toUnitDto)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with id: " + id));
    }

    @Override
    @Transactional
    public UnitDto deleteUnitByName(String name) {
        Units unit = unitRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Unit not found with name: " + name));
        unitRepository.delete(unit);
        return unitMapper.toUnitDto(unit);
    }

    private Units createType(UnitCreateDto unitCreateDto){
        return unitMapper.toUnit(unitCreateDto);
    }
}

package org.senla.service;

import lombok.AllArgsConstructor;
import org.senla.dto.TypeDto;
import org.senla.dto.creators.TypeCreateDto;
import org.senla.dto.mapper.TypeMapper;
import org.senla.entity.Type;
import org.senla.exception.ResourceNotFoundException;
import org.senla.repository.TypeRepository;
import org.senla.service.Impl.TypeServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TypeService implements TypeServiceImp {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;


    @Override
    public List<TypeDto> findAllTypes() {
        return typeRepository.findAll()
                .stream()
                .map(typeMapper::toTypeDto)
                .toList();
    }

    @Override
    @Transactional
    public TypeDto saveType(TypeCreateDto type) {
        return Optional.of(createType(type))
                .map(typeRepository::save)
                .map(typeMapper::toTypeDto)
                .orElseThrow(() -> new ResourceNotFoundException("Incorrect data"));
    }

    @Override
    @Transactional
    public TypeDto updateType(String name, TypeCreateDto newType){
        Type type = typeRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with name: " + name));
        type.setName(newType.getName());
        typeRepository.save(type);
        return typeMapper.toTypeDto(type);
    }

    @Override
    public TypeDto findById(Integer id) {
        return typeRepository.findById(id)
                .map(typeMapper::toTypeDto)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with id: " + id));
    }

    @Override
    @Transactional
    public TypeDto deleteTypeByName(String name) {
        Type type = typeRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with name: " + name));
        typeRepository.delete(type);
        return typeMapper.toTypeDto(type);
    }

    private Type createType(TypeCreateDto typeCreateDto){
        return typeMapper.toType(typeCreateDto);
    }
}

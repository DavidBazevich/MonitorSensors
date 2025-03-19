package org.senla.service;

import lombok.RequiredArgsConstructor;

import org.senla.dto.creators.SensorCreateDto;
import org.senla.dto.SensorDto;
import org.senla.dto.mapper.SensorMapper;
import org.senla.entity.Range;
import org.senla.entity.Sensor;
import org.senla.entity.Type;
import org.senla.entity.Units;
import org.senla.exception.ResourceNotFoundException;
import org.senla.repository.SensorsRepository;
import org.senla.repository.TypeRepository;
import org.senla.repository.UnitRepository;
import org.senla.service.Impl.SensorServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorService implements SensorServiceImp {

    private final SensorsRepository sensorsRepository;
    private final TypeRepository typeRepository;
    private final UnitRepository unitRepository;
    private final SensorMapper sensorMapper;

    @Override
    public List<SensorDto> findAllSensor() {
        return sensorsRepository.findAll()
                .stream()
                .map(sensorMapper::toSensorDto)
                .toList();
    }

    @Override
    @Transactional
    public SensorDto saveSensor(SensorCreateDto sensorCreatedDto) {
       return Optional.of(createSensor(sensorCreatedDto))
               .map(sensorsRepository::save)
               .map(sensorMapper::toSensorDto)
               .orElseThrow(() -> new ResourceNotFoundException("Incorrect data"));
    }

    @Override
    @Transactional
    public SensorDto updateSensor(String name, SensorCreateDto newSensor){
        Units unit = null;
        Type type = typeRepository.findByName(newSensor.getType())
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with name: " + newSensor.getType()));
        if(newSensor.getUnit() != null){
            unit = unitRepository.findByName(newSensor.getUnit())
                    .orElseThrow(() -> new ResourceNotFoundException("Unit not found with name: " + newSensor.getUnit()));
        }
        Sensor sensor = sensorsRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with name: " + name));
        sensor.setName(newSensor.getName());
        sensor.setModel(newSensor.getModel());
        sensor.setRange(newSensor.getRange());
        sensor.setType(type);
        sensor.setUnit(unit);
        sensor.setLocation(newSensor.getLocation());
        sensor.setDescription(newSensor.getDescription());
        sensorsRepository.save(sensor);
        return sensorMapper.toSensorDto(sensor);
    }

    @Override
    public SensorDto findById(Integer id) {
        return sensorsRepository.findById(id)
                .map(sensorMapper::toSensorDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with id: " + id));
    }


    @Override
    @Transactional
    public SensorDto deleteSensorById(Integer id) {
        Sensor sensor = sensorsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with id: " + id));
        sensorsRepository.deleteById(id);
        return sensorMapper.toSensorDto(sensor);
    }

    private Sensor createSensor(SensorCreateDto sensorCreateDto) {
        Units unit = null;
        Type type = typeRepository.findByName(sensorCreateDto.getType())
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with name: " + sensorCreateDto.getType()));
        if(sensorCreateDto.getUnit() != null){
            unit = unitRepository.findByName(sensorCreateDto.getUnit())
                    .orElseThrow(() -> new ResourceNotFoundException("Unit not found with name: " + sensorCreateDto.getUnit()));
        }
        return Sensor.builder()
                .name(sensorCreateDto.getName())
                .model(sensorCreateDto.getModel())
                .range(new Range(sensorCreateDto.getRange().getRange_from(),
                        sensorCreateDto.getRange().getRange_to()))
                .type(type)
                .unit(unit)
                .location(sensorCreateDto.getLocation())
                .description(sensorCreateDto.getDescription())
                .build();
    }


}

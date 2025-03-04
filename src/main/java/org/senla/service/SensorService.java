package org.senla.service;

import lombok.RequiredArgsConstructor;

import org.senla.dto.SensorCreateDto;
import org.senla.dto.SensorDto;
import org.senla.dto.mapper.SensorMapper;
import org.senla.exception.ResourceNotFoundException;
import org.senla.repository.SensorsRepository;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorService implements SensorServiceImp{

    private final SensorsRepository sensorsRepository;
    private final SensorMapper sensorMapper;

    @Override
    public List<SensorDto> findAllSensor() {
        return sensorsRepository.findAll()
                .stream()
                .map(sensorMapper::toSensorDto)
                .toList();
    }

    @Override
    public SensorDto saveSensor(SensorCreateDto sensorCreatedDto) { //TODO как обрабатывать исключение
        try {
            sensorsRepository.save(sensorMapper.toSensor(sensorCreatedDto));
            return sensorMapper.toSensorDto(sensorCreatedDto);
        } catch (InvalidDataAccessApiUsageException e){
            return null;
        }
    }

    @Override
    public SensorDto findById(Integer id) {
        return sensorsRepository.findById(id)
                .map(sensorMapper::toSensorDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found with id: " + id));
    }

    @Override
    public void deleteSensorById(Integer id) {
        if (!sensorsRepository.existsById(id)){
            throw new ResourceNotFoundException("Sensor not found with id: " + id); //TODO как правильно обрабатывать
        }
        sensorsRepository.deleteById(id);
    }


}

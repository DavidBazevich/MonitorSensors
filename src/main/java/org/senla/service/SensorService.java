package org.senla.service;

import lombok.RequiredArgsConstructor;

import org.senla.dto.SensorDto;
import org.senla.dto.mapper.SensorMapper;
import org.senla.repository.SensorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService implements SensorServiceImp{

    private final SensorsRepository sensorsRepository;
    private final SensorMapper sensorMapper;

    @Override
    public Optional<SensorDto> findById(Integer id) {
        return sensorsRepository.findById(id)
                .map(sensorMapper::toSensorDto);
    }

}

package org.senla.service;

import org.senla.dto.SensorDto;

import java.util.List;
import java.util.Optional;

public interface SensorServiceImp {
//    List<SensorDto> findAllSensor();
//    void saveSensor(SensorDto sensorDto);
    Optional<SensorDto> findById(Integer id);
//    void deleteSensorById(Integer id);
}

package org.senla.service;

import org.senla.dto.SensorCreateDto;
import org.senla.dto.SensorDto;

import java.util.List;

public interface SensorServiceImp {
    List<SensorDto> findAllSensor();
    SensorDto saveSensor(SensorCreateDto sensor);
    SensorDto findById(Integer id);
    void deleteSensorById(Integer id);
}

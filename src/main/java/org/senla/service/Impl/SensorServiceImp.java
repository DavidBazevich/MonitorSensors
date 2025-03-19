package org.senla.service.Impl;

import org.senla.dto.creators.SensorCreateDto;
import org.senla.dto.SensorDto;

import java.util.List;

public interface SensorServiceImp {
    List<SensorDto> findAllSensor();
    SensorDto saveSensor(SensorCreateDto sensor);
    SensorDto updateSensor(String name, SensorCreateDto sensor);
    SensorDto findById(Integer id);
    SensorDto deleteSensorById(Integer id);
}

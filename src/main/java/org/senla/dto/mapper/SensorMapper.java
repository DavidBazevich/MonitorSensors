package org.senla.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.senla.dto.SensorDto;
import org.senla.entity.Sensor;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SensorMapper {
    SensorDto toSensorDto(Sensor sensor);
    Sensor toSensor(SensorDto sensorDto);
    List<SensorDto> toSensorDtoList(List<Sensor> sensors);
}

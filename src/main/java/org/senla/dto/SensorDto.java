package org.senla.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.senla.entity.Range;
import org.senla.entity.Type;
import org.senla.entity.Units;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorDto{
    private Integer id;
    private String name;
    private String model;
    private Range range; //TODO надо с json разобраться
    private Type type;
    private Units unit;
    private String location;
    private String description;

}

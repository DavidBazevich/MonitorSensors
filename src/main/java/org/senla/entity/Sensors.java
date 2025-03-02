package org.senla.entity;

import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@Table(name = "sensors")
public class Sensors {

    @Id
    private Long id;
    private String name;
    private String model;





}

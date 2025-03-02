package org.senla.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sensors")
public class Sensors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @Size(max = 15)
    private String model;
    private Integer range; //TODO надо с json разобраться

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private SensorType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Units unit;

    @Size(max = 40)
    private String location;
    @Size(max = 200)
    private String description;

}

package org.senla.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"id", "unit", "type", "range"})
@EqualsAndHashCode(of = "name")
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String model;
    private Range range; //TODO надо с json разобраться
    @ManyToOne(fetch = FetchType.LAZY)
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Units unit;
    private String location;
    private String description;

}

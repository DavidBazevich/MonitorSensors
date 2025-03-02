package org.senla.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "units")
public class Units {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String unit;
    @OneToMany(mappedBy = "unit")
    private List<Sensors> sensorsList;

}

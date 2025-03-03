package org.senla.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "unit")
public class Units {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String unit;
    @OneToMany(mappedBy = "unit")
    private List<Sensor> sensorsList;

}

package org.senla.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @OneToMany(mappedBy = "type")
    private List<Sensor> sensorsList;
}

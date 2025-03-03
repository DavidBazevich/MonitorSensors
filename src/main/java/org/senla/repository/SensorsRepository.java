package org.senla.repository;

import org.senla.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}

package ruvictor.SensorRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ruvictor.SensorRest.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {
}

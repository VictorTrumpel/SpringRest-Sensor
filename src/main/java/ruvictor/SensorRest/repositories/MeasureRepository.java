package ruvictor.SensorRest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ruvictor.SensorRest.models.Measure;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

}

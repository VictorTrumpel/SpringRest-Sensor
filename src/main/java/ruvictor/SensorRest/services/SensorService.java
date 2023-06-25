package ruvictor.SensorRest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ruvictor.SensorRest.errors.SensorAlreadyExistExeption;
import ruvictor.SensorRest.models.Sensor;
import ruvictor.SensorRest.repositories.SensorRepository;

@Service
@Transactional(readOnly = true)
public class SensorService {
  private final SensorRepository sensorRepository;

  @Autowired
  public SensorService(SensorRepository sensorRepository) {
    this.sensorRepository = sensorRepository;
  }

  public List<Sensor> findAll() {
    return sensorRepository.findAll();
  }

  public Optional<Sensor> findById(String sensorName) {
    return sensorRepository.findById(sensorName);
  }

  @Transactional
  public void save(Sensor sensor) {
    Optional<Sensor> optionalSesnsor = sensorRepository.findById(sensor.getName());

    System.out.println("optionalSesnsor.isPresent() : " + optionalSesnsor.isPresent());

    if (optionalSesnsor.isPresent())
      throw new SensorAlreadyExistExeption();

    sensorRepository.save(sensor);
  }
}

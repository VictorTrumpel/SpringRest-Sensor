package ruvictor.SensorRest.errors;

public class SensorAlreadyExistExeption extends RuntimeException {
  public SensorAlreadyExistExeption() {
    super("Sensor already exist");
  }
}

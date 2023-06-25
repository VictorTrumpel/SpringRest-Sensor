package ruvictor.SensorRest.errors;

public class SensorNotExistExeption extends RuntimeException {
  public SensorNotExistExeption() {
    super("Sensor not exist!");
  }

}

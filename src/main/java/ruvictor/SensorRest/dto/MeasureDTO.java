package ruvictor.SensorRest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MeasureDTO {

  @NotNull
  @Min(value = -237, message = "Temperature should be greater than -237")
  @Max(value = 237, message = "Temperature should be less than 237")
  private Integer temperature;

  @NotNull
  private Boolean raining;

  @NotNull
  @Size(min = 1, max = 200, message = "Should be between 1 and 200")
  private String sensorName;

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public Boolean getRaining() {
    return raining;
  }

  public void setRaining(Boolean raining) {
    this.raining = raining;
  }

  public String getSensorName() {
    return sensorName;
  }

  public void setSensorName(String sensor_name) {
    this.sensorName = sensor_name;
  }

}

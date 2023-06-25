package ruvictor.SensorRest.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class SensorDTO {

  @NotNull
  @Size(min = 1, max = 200, message = "Should be between 1 and 200")
  private String name;

  public SensorDTO() {
  }

  public SensorDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "SensorDTO [name=" + name + "]";
  }
}

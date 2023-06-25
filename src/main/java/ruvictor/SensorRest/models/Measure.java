package ruvictor.SensorRest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "measure")
public class Measure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "measure_id")
  private int measureId;

  @NotNull
  @Min(value = -237, message = "Temperature should be greater than -237")
  @Max(value = 237, message = "Temperature should be less than 237")
  @Column(name = "temperature")
  private int temperature;

  @NotNull
  @Column(name = "raining")
  private Boolean raining;

  @ManyToOne()
  @JoinColumn(name = "sensor_name", referencedColumnName = "name")
  private Sensor sensor;

  public Measure() {

  }

  public int getMeasureId() {
    return measureId;
  }

  public void setMeasureId(int measureId) {
    this.measureId = measureId;
  }

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

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  @Override
  public String toString() {
    return "Measure [measureId=" + measureId + ", temperature=" + temperature + ", raining=" + raining + ", sensor="
        + sensor + "]";
  }
}

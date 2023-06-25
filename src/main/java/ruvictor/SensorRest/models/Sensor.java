package ruvictor.SensorRest.models;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sensor")
public class Sensor {

  @Id
  @Column(name = "name")
  @NonNull
  @Size(min = 1, max = 200, message = "Should be between 1 and 200")
  private String name;

  @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
  private List<Measure> measures;

  public Sensor() {
  }

  public Sensor(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Measure> getMeasures() {
    return measures;
  }

  public void setMeasures(List<Measure> measures) {
    this.measures = measures;
  }

  @Override
  public String toString() {
    return "Sensor [name=" + name + ", measures=" + measures + "]";
  }

}

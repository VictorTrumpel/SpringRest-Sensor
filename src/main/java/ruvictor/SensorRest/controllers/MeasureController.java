package ruvictor.SensorRest.controllers;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ruvictor.SensorRest.dto.MeasureDTO;
import ruvictor.SensorRest.errors.SensorNotExistExeption;
import ruvictor.SensorRest.models.Measure;
import ruvictor.SensorRest.models.Sensor;
import ruvictor.SensorRest.services.MeasureService;
import ruvictor.SensorRest.services.SensorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.validation.FieldError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/measurements")
public class MeasureController {

  private final MeasureService measureService;
  private final SensorService sensorService;

  @Autowired
  public MeasureController(MeasureService measureService, SensorService sensorService) {
    this.measureService = measureService;
    this.sensorService = sensorService;
  }

  @GetMapping()
  public ResponseEntity<List<MeasureDTO>> getAllMeasures() {
    return ResponseEntity.ok(measureService.findAll());
  }

  @GetMapping("/rainyDaysCount")
  public Integer getRainyDaysCount() {
    return measureService.getRainyDaysCount();
  }

  @PostMapping("/add")
  public ResponseEntity<HttpStatus> create(@Valid @RequestBody MeasureDTO measureDTO) {

    Optional<Sensor> sensorOptional = sensorService.findById(measureDTO.getSensorName());

    if (sensorOptional.isEmpty())
      throw new SensorNotExistExeption();

    measureService.save(convertToMeasure(measureDTO, sensorOptional.get()));

    return ResponseEntity.ok(HttpStatus.OK);
  }

  private Measure convertToMeasure(MeasureDTO measureDTO, Sensor sensor) {
    Measure measure = new Measure();

    measure.setRaining(measureDTO.getRaining());
    measure.setTemperature(measureDTO.getTemperature());
    measure.setSensor(sensor);

    return measure;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationSensorException(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();

    StringBuilder errorMessage = new StringBuilder();
    for (FieldError error : fieldErrors) {
      errorMessage.append(error.getField())
          .append(": ")
          .append(error.getDefaultMessage())
          .append("; ");
    }

    return ResponseEntity.badRequest().body(errorMessage.toString());
  }

  @ExceptionHandler(SensorNotExistExeption.class)
  public ResponseEntity<String> handleSensorExistException(SensorNotExistExeption ex) {

    return ResponseEntity.badRequest().body(ex.toString());
  }
}

package ruvictor.SensorRest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ruvictor.SensorRest.dto.SensorDTO;
import ruvictor.SensorRest.errors.SensorAlreadyExistExeption;
import ruvictor.SensorRest.models.Sensor;
import ruvictor.SensorRest.services.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {

  private final SensorService sensorService;

  @Autowired
  public SensorController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  @PostMapping
  public ResponseEntity<HttpStatus> create(@Valid @RequestBody SensorDTO sensorDTO) {

    sensorService.save(convertToSensor(sensorDTO));

    return ResponseEntity.ok(HttpStatus.OK);
  }

  private Sensor convertToSensor(SensorDTO sensorDTO) {
    Sensor sensor = new Sensor(sensorDTO.getName());
    return sensor;
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

  @ExceptionHandler(SensorAlreadyExistExeption.class)
  public ResponseEntity<String> handleSensorExistException(SensorAlreadyExistExeption ex) {

    return ResponseEntity.badRequest().body(ex.toString());
  }
}
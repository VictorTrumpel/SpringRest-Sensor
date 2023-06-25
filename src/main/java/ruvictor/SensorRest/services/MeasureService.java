package ruvictor.SensorRest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ruvictor.SensorRest.dto.MeasureDTO;
import ruvictor.SensorRest.models.Measure;
import ruvictor.SensorRest.repositories.MeasureRepository;

@Service
@Transactional(readOnly = true)
public class MeasureService {
  private MeasureRepository measureRepository;

  @Autowired
  public MeasureService(MeasureRepository measureRepository) {
    this.measureRepository = measureRepository;
  }

  public List<MeasureDTO> findAll() {
    List<Measure> measures = measureRepository.findAll();

    List<MeasureDTO> measureDTOs = new ArrayList<>();

    for (Measure measure : measures) {
      measureDTOs.add(convertToMeasureDTO(measure));
    }

    return measureDTOs;
  }

  public Integer getRainyDaysCount() {
    List<Measure> measures = measureRepository.findAll();

    Integer rainyDaysCount = 0;

    for (Measure measure : measures) {
      if (measure.getRaining())
        rainyDaysCount += 1;
    }

    return rainyDaysCount;
  }

  @Transactional
  public void save(Measure measure) {
    measureRepository.save(measure);
  }

  private MeasureDTO convertToMeasureDTO(Measure measure) {
    MeasureDTO measureDTO = new MeasureDTO();

    measureDTO.setRaining(measure.getRaining());
    measureDTO.setSensorName(measure.getSensor().getName());
    measureDTO.setTemperature(measure.getTemperature());

    return measureDTO;
  }

}

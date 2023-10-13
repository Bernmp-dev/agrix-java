package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/** Crop Data Tranfer Object. */
public record CropDto(
        Long id,
        Long farmId,
        String name,
        Double plantedArea,
        LocalDate plantedDate,
        LocalDate harvestDate
) {

  /** Crop constructor. */
  public Crop toCrop() {
    return new Crop(name, plantedArea, plantedDate, harvestDate);
  }
}
package com.agrix.dto;

import com.agrix.models.entities.Crop;
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

  /**
   * Constructor and validation for CropDto.
   * @param id Crop id.
   * @param farmId Farm id.
   * @param name Crop name.
   * @param plantedArea Planted area.
   * @param plantedDate Planted date.
   * @param harvestDate Harvest date.
   */
  public CropDto {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio!");
    }
    if (plantedArea == null || plantedArea <= 0) {
      throw new IllegalArgumentException("Área plantada não pode ser nula ou menor que 0!");
    }
    if (plantedDate == null) {
      throw new IllegalArgumentException("Data de plantio não pode ser nula!");
    }
    if (harvestDate == null) {
      throw new IllegalArgumentException("Data de colheita não pode ser nula!");
    }
  }

  /**
   * Converts CropDto to Crop entity.
   * @return Crop entity.
   */
  public Crop toEntity() {
    return new Crop(
            name,
            plantedArea,
            plantedDate,
            harvestDate
    );
  }
}
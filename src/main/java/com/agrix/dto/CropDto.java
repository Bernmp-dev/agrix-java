package com.agrix.dto;

import com.agrix.models.entities.Crop;
import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

/** Crop Data Tranfer Object. */
public record CropDto(
        Long id,
        Long farmId,
        @NotBlank(message = "Nome não pode ser nulo ou vazio!")
        String name,
        @Min(value = 0, message = "Área plantada não pode ser nula ou menor que 0!")
        Double plantedArea,
        @NotNull(message = "Data de plantio não pode ser nula!")
        @PastOrPresent(message = "Data de plantio não pode ser no futuro!")
        LocalDate plantedDate,
        @NotNull(message = "Data de colheita não pode ser nula!")
        @PastOrPresent(message = "Data de colheita não pode ser no futuro!")
        LocalDate harvestDate
) {

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
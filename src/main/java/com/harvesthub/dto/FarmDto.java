package com.harvesthub.dto;

import com.harvesthub.models.entities.Farm;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

/** Farm Data Tranfer Object. */
public record FarmDto(
    @Schema(description = "Farm's id", example = "1")
    Long id,
    @NotBlank(message = "Nome não pode ser nulo ou vazio!")
    @Schema(description = "Farm's name", example = "Fazenda do João")
    String name,
    @DecimalMin(
      value = "1.0",
      message = "Tamanho não pode ser nulo ou menor que 1!")
    @Schema(description = "Farm's size", example = "100.0")
    Double size
) {
  /**
   * Converts FarmDto to Farm entity.

   * @return Farm entity.
   */
  public Farm toFarm() {
    return new Farm(name, size);
  }
}
package com.agrix.dto;

import com.agrix.models.entities.Fertilizer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/** Fertilizer Data Tranfer Object. */
public record FertilizerDto(
    @NotBlank(message = "Nome não pode ser nulo ou vazio!")
    @Schema(description = "Fertilizer's name", example = "Adubo 1")
    String name,
    @NotBlank(message = "Marca não pode ser nula ou vazia!")
    @Schema(description = "Fertilizer's brand", example = "Marca 1")
    String brand,
    @NotBlank(message = "Composição não pode ser nula ou vazia!")
    @Schema(description = "Fertilizer's composition", example = "Composição 1")
    String composition
) {

  /** Convert to Fertilizer Entity. */
  public Fertilizer toFertilizer() {
    return new Fertilizer(name(), brand(), composition());
  }
}
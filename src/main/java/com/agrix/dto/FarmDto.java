package com.agrix.dto;

import com.agrix.models.entities.Farm;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

/** Farm Data Tranfer Object. */
public record FarmDto(
  @NotBlank(message = "Nome não pode ser nulo ou vazio!")
  String name,
  @DecimalMin(
    value = "1.0",
    message = "Tamanho não pode ser nulo ou menor que 1!")
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
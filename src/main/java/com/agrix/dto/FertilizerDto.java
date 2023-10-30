package com.agrix.dto;

import com.agrix.models.entities.Fertilizer;
import jakarta.validation.constraints.NotBlank;

/** Fertilizer Data Tranfer Object. */
public record FertilizerDto(
  @NotBlank(message = "Nome não pode ser nulo ou vazio!")
  String name,
  @NotBlank(message = "Marca não pode ser nula ou vazia!")
  String brand,
  @NotBlank(message = "Composição não pode ser nula ou vazia!")
  String composition
) {

  /** Convert to Fertilizer Entity. */
  public Fertilizer toFertilizer() {
    return new Fertilizer(name(), brand(), composition());
  }
}
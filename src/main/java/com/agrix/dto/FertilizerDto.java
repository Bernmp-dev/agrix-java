package com.agrix.dto;

import com.agrix.models.entities.Fertilizer;

/** Fertilizer Data Tranfer Object. */
public record FertilizerDto(String name, String brand, String composition) {

  /**
   * Constructor and validation for FertilizerDto.
   * @param name Fertilizer name.
   * @param brand Fertilizer brand.
   * @param composition Fertilizer composition.
   */
  public FertilizerDto {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio!");
    }
    if (brand == null || brand.isBlank()) {
      throw new IllegalArgumentException("Marca não pode ser nula ou vazia!");
    }
    if (composition == null || composition.isBlank()) {
      throw new IllegalArgumentException("Composição não pode ser nula ou vazia!");
    }
  }

  /** Convert to Fertilizer Entity. */
  public Fertilizer toFertilizer() {
    return new Fertilizer(name(), brand(), composition());
  }
}
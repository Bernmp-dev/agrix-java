package com.agrix.dto;

import com.agrix.models.entities.Farm;

/** Farm Data Tranfer Object. */
public record FarmDto(String name, Double size) {

  /**
   * Constructor and validation for FarmDto.
   * @param name Farm name.
   * @param size Farm size.
   */
  public FarmDto {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Nome não pode ser nulo ou vazio!");
    }
    if (size == null || size <= 0) {
      throw new IllegalArgumentException("Tamanho não pode ser nulo ou menor que 0!");
    }
  }

  /**
   * Converts FarmDto to Farm entity.
   * @return Farm entity.
   */
  public Farm toFarm() {
    return new Farm(name, size);
  }
}
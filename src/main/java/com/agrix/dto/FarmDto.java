package com.agrix.dto;

import com.agrix.models.entities.Farm;

/** Farm Data Tranfer Object. */
public record FarmDto(Long id, String name, Double size) {

  public Farm toFarm() {
    return new Farm(name, size);
  }
}
package com.agrix.controllers;

import com.agrix.controllers.annotations.farm.*;
import com.agrix.dto.FarmDto;
import com.agrix.models.entities.Farm;
import com.agrix.services.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/** Farm Controller. */
@FarmControllerConfig
public class FarmController {

  @Autowired
  private FarmService farmService;

  /** Create a farm. */
  @PostMapping
  @CreateFarmConfig
  public FarmDto createFarm(@Valid @RequestBody FarmDto farmDto) {
    return farmService.createFarm(farmDto.toFarm());
  }

  @GetMapping
  @GetAllFarmsConfig
  public List<Farm> getAllFarms() {
    return farmService.getAllFarms();
  }

  @GetMapping("/{id}")
  @GetFarmByIdConfig
  public Farm getFarmById(@PathVariable Long id) {
    return farmService.getFarmById(id);
  }

  @DeleteMapping
  @DeleteAllFarmsConfig
  public String deleteAllFarms() {
    return farmService.deleteAllFarms();
  }
}
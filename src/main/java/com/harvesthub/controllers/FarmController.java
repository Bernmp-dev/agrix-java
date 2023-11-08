package com.harvesthub.controllers;

import com.harvesthub.controllers.annotations.farm.CreateFarmConfig;
import com.harvesthub.controllers.annotations.farm.DeleteAllFarmsConfig;
import com.harvesthub.controllers.annotations.farm.FarmControllerConfig;
import com.harvesthub.controllers.annotations.farm.GetAllFarmsConfig;
import com.harvesthub.controllers.annotations.farm.GetFarmByIdConfig;
import com.harvesthub.dto.FarmDto;
import com.harvesthub.models.entities.Farm;
import com.harvesthub.services.FarmService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
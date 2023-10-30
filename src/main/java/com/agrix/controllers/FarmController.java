package com.agrix.controllers;

import com.agrix.dto.FarmDto;
import com.agrix.models.entities.Farm;
import com.agrix.services.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Farm Controller. */
@RestController
@RequestMapping("/farms")
public class FarmController {

  @Autowired
  private FarmService farmService;

  /** Create a farm. */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Farm createFarm(@Valid @RequestBody FarmDto farmDto) {
    return farmService.createFarm(farmDto.toFarm());
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'MANAGER')")
  public Iterable<Farm> getAllFarms() {
    return farmService.getAllFarms();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Farm getFarmById(@PathVariable Long id) {
    return farmService.getFarmById(id);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.OK)
  public String deleteAllFarms() {
    return farmService.deleteAllFarms();
  }
}
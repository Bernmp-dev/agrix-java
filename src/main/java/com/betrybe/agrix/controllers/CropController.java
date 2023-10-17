package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Crop Controller. */
@RestController
public class CropController {

  @Autowired
  private CropService cropService;

  @GetMapping("/crops")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
  public Iterable<CropDto> getAllCrops() {
    return cropService.getAllCrops();
  }

  @GetMapping("/crops/{cropId}")
  @ResponseStatus(HttpStatus.OK)
  public CropDto getCropById(@PathVariable Long cropId) {
    return cropService.getCropById(cropId).toCropDto();
  }

  @GetMapping("/farms/{farmId}/crops")
  @ResponseStatus(HttpStatus.OK)
  public Iterable<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) {
    return cropService.getAllCropsByFarmId(farmId);
  }

  /** Find crops by harvest date between. */
  @GetMapping("/crops/search")
  public List<CropDto> searchCrops(
      @RequestParam("start") String startDate,
      @RequestParam("end") String endDate
  ) {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);
    return cropService.findByHarvestDateBetween(start, end);
  }

  @PostMapping("/farms/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
    return cropService.createCrop(farmId, crop);
  }
}
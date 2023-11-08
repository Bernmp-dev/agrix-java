package com.harvesthub.controllers;

import com.harvesthub.controllers.annotations.crop.CreateCropConfig;
import com.harvesthub.controllers.annotations.crop.CropControllerConfig;
import com.harvesthub.controllers.annotations.crop.GetAllCropsByFarmIdConfig;
import com.harvesthub.controllers.annotations.crop.GetAllCropsConfig;
import com.harvesthub.controllers.annotations.crop.GetCropByIdConfig;
import com.harvesthub.controllers.annotations.crop.SearchCropsConfig;
import com.harvesthub.dto.CropDto;
import com.harvesthub.services.CropService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/** Crop Controller. */
@CropControllerConfig
public class CropController {

  @Autowired
  private CropService cropService;

  @PostMapping("/farm/{farmId}")
  @CreateCropConfig
  public CropDto createCrop(
      @PathVariable Long farmId,
      @Valid @RequestBody CropDto cropDto
  ) {
    return cropService.createCrop(farmId, cropDto.toEntity());
  }

  @GetMapping
  @GetAllCropsConfig
  public List<CropDto> getAllCrops() {
    return cropService.getAllCrops();
  }

  @GetMapping("/{cropId}")
  @GetCropByIdConfig
  public CropDto getCropById(@PathVariable Long cropId) {
    return cropService.getCropById(cropId).toCropDto();
  }

  @GetMapping("/farm/{farmId}")
  @GetAllCropsByFarmIdConfig
  public List<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) {
    return cropService.getAllCropsByFarmId(farmId);
  }

  /** Find crops by harvest date between. */
  @GetMapping("/search")
  @SearchCropsConfig
  public List<CropDto> searchCrops(
      @RequestParam("start") String startDate,
      @RequestParam("end") String endDate
  ) {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);
    return cropService.findByHarvestDateBetween(start, end);
  }
}
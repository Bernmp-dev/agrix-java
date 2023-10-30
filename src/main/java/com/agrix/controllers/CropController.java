package com.agrix.controllers;

import com.agrix.dto.CropDto;
import com.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Crop Controller. */
@RestController
@RequestMapping("/crops")
@Tag(name = "Crops", description = "Crops management APIs")
public class CropController {

  @Autowired
  private CropService cropService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
  @Operation(summary = "Get all crops")
  public Iterable<CropDto> getAllCrops() {
    return cropService.getAllCrops();
  }

  @GetMapping("/{cropId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get crop by id")
  public CropDto getCropById(@PathVariable Long cropId) {
    return cropService.getCropById(cropId).toCropDto();
  }

  @GetMapping("/farm/{farmId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all crops by farm id")
  public Iterable<CropDto> getAllCropsByFarmId(@PathVariable Long farmId) {
    return cropService.getAllCropsByFarmId(farmId);
  }

  /** Find crops by harvest date between. */
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Find crops by harvest date between")
  public List<CropDto> searchCrops(
      @RequestParam("start") String startDate,
      @RequestParam("end") String endDate
  ) {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);
    return cropService.findByHarvestDateBetween(start, end);
  }

  @PostMapping("/farm/{farmId}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create crop")
  public CropDto createCrop(
    @PathVariable Long farmId,
    @Valid @RequestBody CropDto cropDto
  ) {
    return cropService.createCrop(farmId, cropDto.toEntity());
  }
}
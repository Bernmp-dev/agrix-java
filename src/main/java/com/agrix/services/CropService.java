package com.agrix.services;

import com.agrix.dto.CropDto;
import com.agrix.exceptions.CropNotFoundException;
import com.agrix.models.repositories.CropRepository;
import com.agrix.models.entities.Crop;
import com.agrix.models.entities.Farm;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Crop Service. */
@Service
public class CropService {

  @Autowired
  private CropRepository cropRepository;
  @Autowired
  private FarmService farmService;

  /** Create a crop. */
  @Transactional
  public CropDto createCrop(Long farmId, Crop crop) {
    Farm farm = farmService.getFarmById(farmId);
    crop.setFarm(farm);

    return cropRepository.save(crop).toCropDto();
  }

  /** Get crop by id. */
  public Crop getCropById(Long id) {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  /** Delete a crop. */
  public Iterable<CropDto> getAllCrops() {
    return cropRepository
        .findAll()
        .stream()
        .map(Crop::toCropDto)
        .toList();
  }

  /** Delete all crops. */
  public Iterable<CropDto> getAllCropsByFarmId(Long farmId) {
    return farmService
        .getFarmById(farmId)
        .getCrops()
        .stream()
        .map(Crop::toCropDto)
        .toList();
  }

  /** Find crops by harvest date between. */
  public List<CropDto> findByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository
            .findByHarvestDateBetween(start, end)
            .stream()
            .map(Crop::toCropDto)
            .toList();
  }
}
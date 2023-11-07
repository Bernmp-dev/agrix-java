package com.agrix.services;

import com.agrix.dto.CropDto;
import com.agrix.exceptions.CropNotFoundException;
import com.agrix.models.repositories.CropRepository;
import com.agrix.models.entities.Crop;
import com.agrix.models.entities.Farm;
import java.time.LocalDate;
import java.util.List;
import com.agrix.models.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Crop Service. */
@Service
public class CropService {

  @Autowired
  private FarmRepository farmRepository;
  @Autowired
  private CropRepository cropRepository;
  @Autowired
  private FarmService farmService;

    /** Create a crop. */
    @Transactional
    public CropDto createCrop(Long farmId, Crop crop) {
      Farm farm = farmService.getFarmById(farmId);
      crop.setFarm(farm);
      farm.addCrop(crop);

      farmRepository.save(farm);  // Salva a Farm, que também salva a Crop devido à cascata

      return cropRepository
        .findFirstByOrderByIdDesc()
        .toCropDto();
    }

  /** Get crop by id. */
  public Crop getCropById(Long id) {
    return cropRepository
      .findById(id)
      .orElseThrow(CropNotFoundException::new);
  }

  /** Delete a crop. */
  public List<CropDto> getAllCrops() {
    List<CropDto> cropDtos = cropRepository
      .findAll()
      .stream()
      .map(Crop::toCropDto)
      .toList();

    if (cropDtos.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropDtos;
  }

  /** Delete all crops. */
  public List<CropDto> getAllCropsByFarmId(Long farmId) {
    List<CropDto> cropDtos = farmService
      .getFarmById(farmId)
      .getCrops()
      .stream()
      .map(Crop::toCropDto)
      .toList();

    if (cropDtos.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropDtos;
  }

  /** Find crops by harvest date between. */
  public List<CropDto> findByHarvestDateBetween(LocalDate start, LocalDate end) {
    List<CropDto> cropDtos = cropRepository
      .findByHarvestDateBetween(start, end)
      .stream()
      .map(Crop::toCropDto)
      .toList();

    if (cropDtos.isEmpty()) {
      throw new CropNotFoundException();
    }

    return cropDtos;
  }
}
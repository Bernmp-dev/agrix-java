package com.harvesthub.services;

import com.harvesthub.exceptions.FertilizerNotFound;
import com.harvesthub.models.entities.Crop;
import com.harvesthub.models.entities.Fertilizer;
import com.harvesthub.models.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Fertilizer service. */
@Service
public class FertilizerService {
  @Autowired
  private FertilizerRepository fertilizerRepository;
  @Autowired
  private CropService cropService;

  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Get a fertilizer by id.
   *
   * @param id fertilizer id.
   * @return the fertilizer.
   * @throws FertilizerNotFound if the fertilizer is not found.
   */
  public Fertilizer getFertilizerById(Long id) {
    return fertilizerRepository
      .findById(id)
      .orElseThrow(FertilizerNotFound::new);
  }

  /** add a fertilizer to a crop. */
  public void addFertilizerToCrop(Long cropId, Long fertilizerId) {
    Crop crop = cropService.getCropById(cropId);
    Fertilizer fertilizer = getFertilizerById(fertilizerId);
    crop.addFertilizer(fertilizer);
    cropService.createCrop(crop.getFarm().getId(), crop);

    fertilizer.addCrop(crop);
    createFertilizer(fertilizer);
  }

  /** get all fertilizers from a crop. */
  public List<Fertilizer> getFertilizersByCropId(Long cropId) {
    Crop crop = cropService.getCropById(cropId);
    return crop.getFertilizers();
  }
}
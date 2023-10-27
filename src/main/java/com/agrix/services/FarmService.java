package com.agrix.services;

import com.agrix.exceptions.FarmAlreadyExistsException;
import com.agrix.exceptions.FarmNotFoundException;
import com.agrix.models.entities.Farm;
import com.agrix.models.repositories.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Farm Service. */
@Service
public class FarmService {
  @Autowired
  private FarmRepository farmRepository;

  /** Create a farm. */
  public Farm createFarm(Farm farm) {
    farmRepository.findByName(farm.getName())
      .ifPresent(existingFarm -> {
      throw new FarmAlreadyExistsException();
    });

    return farmRepository.save(farm);
  }

  public Farm getFarmById(Long id) {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  public Iterable<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /** Delete all farms. */
  @Transactional
  public String deleteAllFarms() {
    farmRepository.deleteAll();

    return "All farms deleted";
  }
}
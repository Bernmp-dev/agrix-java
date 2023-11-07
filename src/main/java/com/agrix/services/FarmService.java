package com.agrix.services;

import com.agrix.dto.FarmDto;
import com.agrix.exceptions.FarmAlreadyExistsException;
import com.agrix.exceptions.FarmNotFoundException;
import com.agrix.models.entities.Farm;
import com.agrix.models.repositories.FarmRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Farm Service. */
@Service
public class FarmService {
  @Autowired
  private FarmRepository farmRepository;

  /** Create a farm. */
  public FarmDto createFarm(Farm farm) {
    farmRepository.findByName(farm.getName())
        .ifPresent(existingFarm -> {
          throw new FarmAlreadyExistsException();
        });

    return farmRepository.save(farm).toFarmDto();
  }

  /** Get farm by id. */
  public Farm getFarmById(Long id) {
    return farmRepository
      .findById(id)
      .orElseThrow(FarmNotFoundException::new);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /** Delete all farms. */
  @Transactional
  public String deleteAllFarms() {
    farmRepository.deleteAll();

    return "All farms deleted";
  }
}
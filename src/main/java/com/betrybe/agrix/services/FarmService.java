package com.betrybe.agrix.services;

//import com.betrybe.agrix.exceptions.FarmAlreadyExistsException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
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

    //    farmRepository.findAll().forEach(farm1 -> {
    //      if (farm1.getName().equals(farm.getName())) {
    //        throw new FarmAlreadyExistsException();
    //      }
    //});

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
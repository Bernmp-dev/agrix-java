package com.betrybe.agrix.controllers;

import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Fertilizer controller. */
@RestController
public class FertilizerController {
  @Autowired
  private FertilizerService fertilizerService;

  @GetMapping("/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerService.getAllFertilizers();
  }

  @GetMapping("/fertilizers/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Fertilizer getFertilizerById(@PathVariable Long id) {
    return fertilizerService.getFertilizerById(id);
  }

  @PostMapping("/fertilizers")
  @ResponseStatus(HttpStatus.CREATED)
  public Fertilizer createFertilizer(@RequestBody Fertilizer fertilizer) {
    return fertilizerService.createFertilizer(fertilizer);
  }

  @PostMapping("/crops/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String addFertilizerToCrop(
          @PathVariable Long cropId,
          @PathVariable Long fertilizerId
  ) {
    fertilizerService.addFertilizerToCrop(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }

  @GetMapping("/crops/{cropId}/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<Fertilizer> getFertilizersByCropId(@PathVariable Long cropId) {
    return fertilizerService.getFertilizersByCropId(cropId);
  }
}
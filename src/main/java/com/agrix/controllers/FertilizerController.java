package com.agrix.controllers;

import com.agrix.controllers.annotations.fertilizer.FertilizersControllerConfig;
import com.agrix.dto.FertilizerDto;
import com.agrix.models.entities.Fertilizer;
import com.agrix.services.FertilizerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Fertilizer controller. */
@RestController
@FertilizersControllerConfig
public class FertilizerController {
  @Autowired
  private FertilizerService fertilizerService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasAnyRole('ADMIN')")
  @Operation(summary = "Get all fertilizers")
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerService.getAllFertilizers();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get fertilizer by id")
  public Fertilizer getFertilizerById(@PathVariable Long id) {
    return fertilizerService.getFertilizerById(id);
  }

  @GetMapping("/crops/{cropId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get fertilizers by crop id")
  public List<Fertilizer> getFertilizersByCropId(@PathVariable Long cropId) {
    return fertilizerService.getFertilizersByCropId(cropId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a fertilizer")
  public Fertilizer createFertilizer(@Valid @RequestBody FertilizerDto fertilizerDto) {
    return fertilizerService.createFertilizer(fertilizerDto.toFertilizer());
  }

  @PostMapping("/{fertilizerId}/crops/{cropId}/")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Add fertilizer to crop")
  public String addFertilizerToCrop(
          @PathVariable Long cropId,
          @PathVariable Long fertilizerId
  ) {
    fertilizerService.addFertilizerToCrop(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }
}
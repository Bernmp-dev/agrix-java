package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/** Farm Repository. */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
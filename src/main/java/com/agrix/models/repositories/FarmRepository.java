package com.agrix.models.repositories;

import com.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/** Farm Repository. */
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
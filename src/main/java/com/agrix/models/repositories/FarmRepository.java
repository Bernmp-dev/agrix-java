package com.agrix.models.repositories;

import com.agrix.models.entities.Farm;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** Farm Repository. */
public interface FarmRepository extends JpaRepository<Farm, Long> {
  
  Optional<Farm> findByName(String name);
}
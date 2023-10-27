package com.agrix.models.repositories;

import com.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/** Farm Repository. */
public interface FarmRepository extends JpaRepository<Farm, Long> {
  
  Optional<Farm> findByName(String name);
}
package com.betrybe.agrix.models;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Database Seeder. */
@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final FarmRepository farmRepository;

  public DatabaseSeeder(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    //seedFarms();
  }

  @Transactional
  private void seedFarms() {
    List<Farm> farms = new ArrayList<>();

    farms.add(new Farm("Sunnydale Farm", 250.0));
    farms.add(new Farm("Maplewood Acres", 120.0));
    farms.add(new Farm("Green Pastures", 350.0));
    farms.add(new Farm("Meadowbrook Ranch", 500.0));
    farms.add(new Farm("Willow Creek", 150.0));

    farmRepository.saveAll(farms);
  }
}
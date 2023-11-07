package com.agrix.models;

import com.agrix.dto.TokenDto;
import com.agrix.models.entities.Crop;
import com.agrix.models.entities.Farm;
import com.agrix.models.entities.Person;
import com.agrix.models.repositories.FarmRepository;
import com.agrix.models.repositories.PersonRepository;
import com.agrix.security.Role;
import com.agrix.services.TokenService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Database Seeder. */
@Component
public class DatabaseSeeder implements CommandLineRunner {

  @Autowired
  private FarmRepository farmRepository;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private TokenService tokenService;

  @Override
  public void run(String... args) throws Exception {
    seedFarms();
    seedCrops();
    seedUsers();
  }

  @Transactional
  private void seedUsers() {
    List<Person> people = new ArrayList<>();

    Person admin = new Person("admin", passwordEncoder.encode("admin"), Role.ADMIN);
    TokenDto tokenDto;
    tokenDto = new TokenDto(tokenService.generateToken(admin));

    people.add(admin);
    people.add(new Person("user", passwordEncoder.encode("user"), Role.USER));
    people.add(new Person("manager", passwordEncoder.encode("manager"), Role.MANAGER));

    personRepository.saveAll(people);
    System.out.println("Admin token: " + tokenDto);
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

  private void seedCrops() {
    List<Farm> farms;
    farms = farmRepository.findAll();

    List<Crop> crops = new ArrayList<>();

    crops.add(new Crop("Corn", 100.0,
        LocalDate.parse("2022-03-01"), LocalDate.parse("2022-10-01")));

    crops.add(new Crop("Wheat", 80.0,
        LocalDate.parse("2022-05-15"), LocalDate.parse("2022-09-15")));

    crops.add(new Crop("Soybean", 70.0,
        LocalDate.parse("2022-04-01"), LocalDate.parse("2022-11-01")));

    crops.add(new Crop("Rice", 60.0,
        LocalDate.parse("2022-04-15"), LocalDate.parse("2022-10-15")));

    crops.add(new Crop("Barley", 90.0,
        LocalDate.parse("2022-05-01"), LocalDate.parse("2022-10-01")));

    crops.add(new Crop("Oats", 95.0,
        LocalDate.parse("2022-03-15"), LocalDate.parse("2022-09-15")));

    crops.add(new Crop("Rye", 50.0,
        LocalDate.parse("2022-04-01"), LocalDate.parse("2022-09-01")));

    crops.add(new Crop("Sorghum", 40.0,
        LocalDate.parse("2022-06-01"), LocalDate.parse("2022-11-01")));

    crops.get(0).setFarm(farms.get(0));
    crops.get(1).setFarm(farms.get(0));
    crops.get(2).setFarm(farms.get(0));
    crops.get(3).setFarm(farms.get(1));
    crops.get(4).setFarm(farms.get(1));
    crops.get(5).setFarm(farms.get(1));
    crops.get(6).setFarm(farms.get(2));
    crops.get(7).setFarm(farms.get(2));

    farms.get(0).addCrop(crops.get(0));
    farms.get(0).addCrop(crops.get(1));
    farms.get(0).addCrop(crops.get(2));
    farms.get(1).addCrop(crops.get(3));
    farms.get(1).addCrop(crops.get(4));
    farms.get(1).addCrop(crops.get(5));
    farms.get(2).addCrop(crops.get(6));
    farms.get(2).addCrop(crops.get(7));

    farmRepository.saveAll(farms);
  }
}
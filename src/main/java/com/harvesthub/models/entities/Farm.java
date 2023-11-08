package com.harvesthub.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harvesthub.dto.FarmDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/** Farm Entity. */
@Entity
@Table(name = "farm")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Double size;
  @OneToMany(
      mappedBy = "farm",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL
  )
  @JsonIgnore
  private List<Crop> crops;

  /** Empty Farm constructor. */
  public Farm() {
  }

  /** Farm constructor. */
  public Farm(String name, Double size) {
    this.name = name;
    this.size = size;
  }

  public FarmDto toFarmDto() {
    return new FarmDto(id, name, size);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSize() {
    return size;
  }

  public void setSize(Double size) {
    this.size = size;
  }

  public List<Crop> getCrops() {
    return crops;
  }

  public void addCrop(Crop crop) {
    this.crops.add(crop);
  }

  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
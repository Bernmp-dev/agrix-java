package com.betrybe.agrix.exceptions;

/** Fertilizer not found exception. */
public class FertilizerNotFound extends RuntimeException {
  public FertilizerNotFound() {
    super("Fertilizante não encontrado!");
  }
}
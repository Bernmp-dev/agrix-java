package com.betrybe.agrix.exceptions;

/** Crop Not Found Exception. */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
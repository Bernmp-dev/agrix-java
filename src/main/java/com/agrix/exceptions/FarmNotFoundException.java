package com.agrix.exceptions;

/** Farm Not Found Exception. */
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
package com.harvesthub.exceptions;

/** Farm Already Exists Exception. */
public class FarmAlreadyExistsException extends RuntimeException {
  public FarmAlreadyExistsException() {
    super("Fazenda já existe!");
  }
}
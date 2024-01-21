package com.alves.tmpfile.core.exceptions;

public class UnableToSaveFileException extends RuntimeException {

  public UnableToSaveFileException(String filename) {
    super(String.format("Unable to save file '%s'", filename));
  }
  
}
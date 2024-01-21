package com.alves.tmpfile.core.exceptions;

public class UnableToSaveFileException extends RuntimeException {

  public UnableToSaveFileException(String fileName) {
    super(String.format("Unable to save file '%s'", fileName));
  }
  
}
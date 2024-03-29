package com.alves.tmpfile.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToSaveFileException extends RuntimeException {

  public UnableToSaveFileException(String filename) {
    super(String.format("Unable to save file '%s'.", filename));
  }
  
}
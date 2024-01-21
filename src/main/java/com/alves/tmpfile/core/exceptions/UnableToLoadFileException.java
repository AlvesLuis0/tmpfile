package com.alves.tmpfile.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnableToLoadFileException extends RuntimeException {

  public UnableToLoadFileException(String filename) {
    super(String.format("Unable to load file '%s'.", filename));
  }
  
}
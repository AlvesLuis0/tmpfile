package com.alves.tmpfile.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException(String id) {
    super(String.format("File with ID '%s' not found.", id));
  }
  
}
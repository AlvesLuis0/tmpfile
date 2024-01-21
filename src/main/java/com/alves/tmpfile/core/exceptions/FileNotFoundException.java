package com.alves.tmpfile.core.exceptions;

import java.util.UUID;

public class FileNotFoundException extends RuntimeException {

  public FileNotFoundException(UUID id) {
    super(String.format("File with ID '%s' not found.", id));
  }
  
}
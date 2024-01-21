package com.alves.tmpfile.adapters.dtos;

import java.util.UUID;

public record SaveFileResponse(String url) {

  public static SaveFileResponse build(UUID id) {
    return new SaveFileResponse("/upload/" + id);
  }
  
}
package com.alves.tmpfile.core.dtos;

public record SaveFileResponse(String path) {

  public static SaveFileResponse build(String id) {
    return new SaveFileResponse("/upload/" + id);
  }
  
}
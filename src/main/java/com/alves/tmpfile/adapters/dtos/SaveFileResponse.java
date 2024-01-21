package com.alves.tmpfile.adapters.dtos;

public record SaveFileResponse(String url) {

  public static SaveFileResponse build(String id) {
    return new SaveFileResponse("/upload/" + id);
  }
  
}
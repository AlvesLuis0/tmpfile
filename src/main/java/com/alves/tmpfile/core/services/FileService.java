package com.alves.tmpfile.core.services;

import java.util.UUID;

public interface FileService {

  public UUID saveFile(String originalName, byte[] content);
  
}
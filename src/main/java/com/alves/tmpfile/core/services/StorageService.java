package com.alves.tmpfile.core.services;

public interface StorageService {

  public boolean saveFile(String fileName, byte[] content);
  
}
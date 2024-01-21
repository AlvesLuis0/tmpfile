package com.alves.tmpfile.core.services;

public interface StorageService {

  public boolean saveFile(String filename, byte[] content);
  public byte[] loadFile(String filename);
  
}
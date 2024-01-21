package com.alves.tmpfile.core.services;

public interface FileService {

  public String saveFile(String originalFilename, byte[] content);
  public byte[] loadFile(String id);
  
}
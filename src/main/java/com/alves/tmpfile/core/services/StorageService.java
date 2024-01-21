package com.alves.tmpfile.core.services;

import java.io.IOException;

public interface StorageService {

  public boolean saveFile(String fileName, byte[] content) throws IOException;
  
}
package com.alves.tmpfile.core.services;

import com.alves.tmpfile.core.models.FileInfo;

public interface FileService {

  public String saveFile(FileInfo file);
  public FileInfo loadFile(String id);
  
}
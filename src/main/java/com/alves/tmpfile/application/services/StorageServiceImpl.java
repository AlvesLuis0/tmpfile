package com.alves.tmpfile.application.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.alves.tmpfile.config.StorageConfig;
import com.alves.tmpfile.core.services.StorageService;

public class StorageServiceImpl implements StorageService {

  private final StorageConfig storageConfig;

  public StorageServiceImpl(StorageConfig storageConfig) {
    this.storageConfig = storageConfig;
    File storageDir = new File(storageConfig.getPath());
    storageDir.mkdirs();
  }

  @Override
  public boolean saveFile(String fileName, byte[] content) {
    try {
      Path filePath = Path.of(
        storageConfig.getPath(),
        fileName
      );
      Files.write(filePath, content);
      return true;
    }
    catch(IOException exception) {
      exception.printStackTrace();
      return false;
    }
  }

}
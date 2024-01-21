package com.alves.tmpfile.application.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.alves.tmpfile.config.StorageConfig;
import com.alves.tmpfile.core.services.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

  private final StorageConfig storageConfig;

  public StorageServiceImpl(StorageConfig storageConfig) {
    this.storageConfig = storageConfig;
    var storageDir = new File(storageConfig.getPath());
    storageDir.mkdirs();
  }

  @Override
  public boolean saveFile(String filename, byte[] content) {
    try {
      var filePath = Path.of(
        storageConfig.getPath(),
        filename
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
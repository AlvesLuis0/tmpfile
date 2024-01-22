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
      var filepath = Path.of(
        storageConfig.getPath(),
        filename
      );
      Files.write(filepath, content);
      return true;
    }
    catch(IOException exception) {
      exception.printStackTrace();
      return false;
    }
  }

  @Override
  public byte[] loadFile(String filename) {
    var filepath = Path.of(
      storageConfig.getPath(),
      filename
    );
    try {
      return Files.readAllBytes(filepath);
    }
    catch(IOException exception) {
      exception.printStackTrace();
      return null;
    }
  }

  @Override
  public boolean deleteFile(String filename) {
    var filepath = Path.of(storageConfig.getPath(), filename);
    try {
      return Files.deleteIfExists(filepath);
    }
    catch(IOException exception) {
      exception.printStackTrace();
      return false;
    }
  }

}
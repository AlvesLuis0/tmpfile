package com.alves.tmpfile.application.services;

import java.util.Date;
import java.util.UUID;

import com.alves.tmpfile.application.repositories.FileInfoRepository;
import com.alves.tmpfile.core.exceptions.UnableToSaveFileException;
import com.alves.tmpfile.core.models.FileInfo;
import com.alves.tmpfile.core.services.FileService;
import com.alves.tmpfile.core.services.StorageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

  private final StorageService storageService;
  private final FileInfoRepository fileInfoRepository;

  @Override @Transactional
  public UUID saveFile(String originalName, byte[] content) {
    FileInfo file = new FileInfo();
    file.setOriginalName(originalName);
    file.setUploadDate(new Date());
    file.setId(fileInfoRepository.save(file).getId());
    boolean errorOnSaveFile = !storageService.saveFile(
      file.getId().toString(),
      content
    );
    if(errorOnSaveFile) {
      throw new UnableToSaveFileException(originalName);
    }
    return file.getId();
  }
  
}
package com.alves.tmpfile.application.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.alves.tmpfile.adapters.repositories.FileInfoRepository;
import com.alves.tmpfile.core.exceptions.UnableToSaveFileException;
import com.alves.tmpfile.core.models.FileInfo;
import com.alves.tmpfile.core.services.FileService;
import com.alves.tmpfile.core.services.StorageService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

  private final StorageService storageService;
  private final FileInfoRepository fileInfoRepository;

  @Override @Transactional
  public UUID saveFile(String originalName, byte[] content) {
    var file = new FileInfo();
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
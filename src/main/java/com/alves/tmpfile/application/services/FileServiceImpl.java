package com.alves.tmpfile.application.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.alves.tmpfile.adapters.repositories.FileInfoRepository;
import com.alves.tmpfile.core.exceptions.FileNotFoundException;
import com.alves.tmpfile.core.exceptions.UnableToLoadFileException;
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
  public String saveFile(String originalFilename, byte[] content) {
    var file = new FileInfo();
    file.setUploadDate(new Date());
    file.setId(UUID.randomUUID() + originalFilename);
    fileInfoRepository.save(file);
    boolean errorOnSaveFile = !storageService.saveFile(
      file.getId().toString(),
      content
    );
    if(errorOnSaveFile) {
      throw new UnableToSaveFileException(originalFilename);
    }
    return file.getId();
  }

  @Override
  public byte[] loadFile(String id) {
    fileInfoRepository.findById(id)
      .orElseThrow(() -> new FileNotFoundException(id));
    byte[] file = storageService.loadFile(id);
    boolean errorOnLoad = file == null;
    if(errorOnLoad) {
      throw new UnableToLoadFileException(id);
    }
    return file;
  }
  
}
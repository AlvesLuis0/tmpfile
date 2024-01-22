package com.alves.tmpfile.application.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alves.tmpfile.adapters.repositories.FileInfoRepository;
import com.alves.tmpfile.application.utils.Utils;
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
  public String saveFile(FileInfo file) {
    String randomString;
    do {
      randomString = Utils.randomString(6);
    } while(fileInfoRepository.existsById(randomString));
    file.setId(randomString);
    file.setExpirationDate(Utils.getExpirationDate());
    fileInfoRepository.save(file);
    boolean errorOnSaveFile = !storageService.saveFile(
      file.getId(),
      file.getContent()
    );
    if(errorOnSaveFile) {
      throw new UnableToSaveFileException(file.getOriginalFilename());
    }
    return file.getId();
  }

  @Override
  public FileInfo loadFile(String id) {
    FileInfo file = fileInfoRepository.findById(id)
      .orElseThrow(() -> new FileNotFoundException(id));
    file.setContent(storageService.loadFile(id));
    boolean errorOnLoad = file.getContent() == null;
    if(errorOnLoad) {
      throw new UnableToLoadFileException(id);
    }
    return file;
  }

  @Override @Transactional @Scheduled(fixedRate = 24 * 60 * 60 * 1000) // every day
  public void cleanupExpiredFiles() {
    fileInfoRepository.findAll()
      .stream()
      .filter(FileInfo::isExpired)
      .forEach(file -> {
        storageService.deleteFile(file.getId());
        fileInfoRepository.delete(file);
      });
  }
  
}
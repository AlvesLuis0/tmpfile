package com.alves.tmpfile.adapters.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alves.tmpfile.adapters.dtos.SaveFileResponse;
import com.alves.tmpfile.core.services.FileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController @RequestMapping("/upload")
public class FileController {

  private final FileService fileService;

  @PostMapping
  public ResponseEntity<SaveFileResponse> saveFile(@RequestPart MultipartFile file) throws IOException {
    UUID id = fileService.saveFile(
      file.getOriginalFilename(),
      file.getBytes()
    );
    var response = SaveFileResponse.build(id);
    return ResponseEntity.ok(response);
  }
  
}
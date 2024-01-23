package com.alves.tmpfile.adapters.controllers;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alves.tmpfile.core.dtos.SaveFileResponse;
import com.alves.tmpfile.core.models.FileInfo;
import com.alves.tmpfile.core.services.FileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController @RequestMapping("/upload")
public class FileController {

  private final FileService fileService;

  @PostMapping
  public ResponseEntity<SaveFileResponse> saveFile(@RequestPart MultipartFile file) throws IOException {
    var fileInfo = FileInfo.builder()
      .originalFilename(file.getOriginalFilename())
      .content(file.getBytes())
      .build();
    String id = fileService.saveFile(fileInfo);
    var response = SaveFileResponse.build(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity<byte[]> loadFile(@PathVariable String id) {
    FileInfo file = fileService.loadFile(id);
    return ResponseEntity.ok()
      .header("Content-Disposition", "attachment; filename=" + file.getOriginalFilename())
      .body(file.getContent());
  }
  
}
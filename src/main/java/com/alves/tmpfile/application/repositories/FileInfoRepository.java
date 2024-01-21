package com.alves.tmpfile.application.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alves.tmpfile.core.models.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, UUID> {
  
}
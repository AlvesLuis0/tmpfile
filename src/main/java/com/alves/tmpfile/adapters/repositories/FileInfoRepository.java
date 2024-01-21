package com.alves.tmpfile.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.tmpfile.core.models.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, String> {

  // 
  
}
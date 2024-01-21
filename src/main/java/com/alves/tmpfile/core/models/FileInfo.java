package com.alves.tmpfile.core.models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class FileInfo {

  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String originalName;
  private Date uploadDate;
  
}
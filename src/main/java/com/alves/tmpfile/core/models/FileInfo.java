package com.alves.tmpfile.core.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class FileInfo {

  @Id
  private String id;
  private String originalFilename;
  private Date expirationDate;
  @Transient
  private byte[] content;
  
}
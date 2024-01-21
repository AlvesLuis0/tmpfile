package com.alves.tmpfile.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@ConfigurationProperties("storage")
@Configuration
public class StorageConfig {

  private String path;
  
}
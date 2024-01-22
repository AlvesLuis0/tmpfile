package com.alves.tmpfile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@Configuration
public class SchedulerConfig {

  @Bean
  public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    var taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setThreadNamePrefix("scheduled-task-");
    return taskScheduler;
  }
  
}
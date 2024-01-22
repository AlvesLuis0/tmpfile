package com.alves.tmpfile.application.utils;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Date;

public class Utils {

  private final static String CHARS =
    "abcdefghijklmnopqrstuvwxyz" +
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
    "0123456789";

  public static String randomString(int length) {
    var rand = new SecureRandom();
    var string = new StringBuilder();
    int index;
    char character;
    for(int i = 0; i < length; i++) {
      index = rand.nextInt(0, CHARS.length());
      character = CHARS.charAt(index);
      string.append(character);
    }
    return string.toString();
  }

  public static Date getExpirationDate() {
    LocalDate date = LocalDate.now();
    return java.sql.Date.valueOf(date.plusDays(7));
  }
  
}
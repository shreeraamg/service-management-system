package com.servicemanagement.utils;

public class HashPassword {
  private static final String SALT = "$qwertyuiop@#asdfghjkl%*zxcvbnm!";

  public static String hashPassword(String password) {
    char[] substitutionMap = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    StringBuilder substitutedPassword = new StringBuilder();
    for (char c : password.toCharArray()) {
      if (Character.isLetter(c)) {
        char lowerCaseC = Character.toLowerCase(c);
        char substituteChar = substitutionMap[(int) (lowerCaseC - 'a') % substitutionMap.length];
        substitutedPassword.append(Character.isUpperCase(c) ? Character.toUpperCase(substituteChar) : substituteChar);
      } else {
        substitutedPassword.append(c);
      }
    }

    StringBuilder hashedPassword = new StringBuilder();
    for (int i = substitutedPassword.length() - 1; i >= 0; i--) {
      hashedPassword.append(substitutedPassword.charAt(i));
    }

    return hashedPassword.toString() + SALT;
  }
}

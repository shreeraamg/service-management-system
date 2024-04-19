package com.servicemanagement.views;

import java.util.Scanner;

import com.servicemanagement.beans.User;
import com.servicemanagement.services.UserService;

public class Main {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    UserService userService = new UserService();
    User loggedInUser = userService.login("akash@gmail.com", "asdasd");
  }
}

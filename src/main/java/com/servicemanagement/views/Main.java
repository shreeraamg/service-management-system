package com.servicemanagement.views;

import java.util.Scanner;

import com.servicemanagement.beans.User;
import com.servicemanagement.services.UserService;
import com.servicemanagement.utils.HashPassword;

public class Main {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    UserService userService = new UserService();

    // System.out.println("Enter your name");
    // String name = sc.nextLine();
    // System.out.println("Enter your email");
    // String emailId = sc.nextLine();
    // System.out.println("Enter your mobile number");
    // String mobile = sc.nextLine();
    // System.out.println("Enter your password");
    // String password = sc.nextLine();
    // String hashedPassword = HashPassword.hashPassword(password);
    // System.out.println("Enter your address");

    String name = "Shreeraam";
    String emailId = "shreeraam@gmail.com";
    String mobile = "9080706050";
    String hashedPassword = HashPassword.hashPassword("dorabuji");
    String address = "Madipakkam, Chennai";

    System.out.println(userService.addCustomer(new User(name, emailId, mobile, hashedPassword, address)));
  }
}

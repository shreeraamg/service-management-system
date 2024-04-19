package com.servicemanagement.views;

import java.util.List;
import java.util.Scanner;

import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.beans.User;
import com.servicemanagement.beans.Vendor;
import com.servicemanagement.services.BookingService;
import com.servicemanagement.services.UserService;

public class Main {
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    UserService userService = new UserService();
    BookingService bookingService = new BookingService();

    User loggedInUser = userService.login("akash@gmail.com", "asdasd");
    System.out.println(loggedInUser.getAddress());

    List<Vendor> vendorsWithGivenServiceType = bookingService.getVendorsByServiceType(ServiceType.AIR_CONDITIONER);

    for (Vendor v : vendorsWithGivenServiceType) {
      System.out.println(v.getName());
    }
  }
}

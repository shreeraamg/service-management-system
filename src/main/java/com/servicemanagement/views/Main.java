package com.servicemanagement.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.beans.Status;
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
    createBooking(bookingService, loggedInUser);
  }

  public static void createBooking(BookingService bookingService, User loggedInUser) {
    System.out.println("Press 1 for AC Service");
    System.out.println("Press 2 for Refrigerator Service");
    System.out.println("Press 3 for TV Service");
    System.out.println("Press 4 for Washing Machine Service");
    int serviceTypeNumber = sc.nextInt();
    ServiceType serviceType = switch (serviceTypeNumber) {
      case 1 -> ServiceType.AIR_CONDITIONER;
      case 2 -> ServiceType.TELEVISION;
      case 3 -> ServiceType.WASHING_MACHINE;
      case 4 -> ServiceType.REFRIGERATOR;
      default -> null;
    };

    if (serviceType == null) {
      System.err.println("Sorry! You have not picked a valid service type.");
      return;
    }

    System.out.println("Press the corresponding number to choose a vendor");
    List<Vendor> vendors = bookingService.getVendorsByServiceType(serviceType);
    for (int i = 0; i < vendors.size(); i++) {
      System.out.println(i + 1 + " | " + vendors.get(i).getName() + " | " + vendors.get(i).getPrice());
    }
    int selectedVendorNumber = sc.nextInt();
    if (selectedVendorNumber >= vendors.size() || selectedVendorNumber <= 0) {
      System.out.println("Sorry! You have not picked a valid vendor.");
      return;
    }
    Vendor selectedVendor = vendors.get(selectedVendorNumber - 1);

    System.out.println("Enter date: (Format YYYY-MM-DD)");
    sc.nextLine();
    String dateInput = sc.nextLine();
    LocalDate date = LocalDate.parse(dateInput);
    LocalDate currentDate = LocalDate.now();
    if (date.isBefore(currentDate) || date.isEqual(currentDate)) {
      System.out.println("Please Enter a date after today.");
      return;
    }

    int result = bookingService
        .createBooking(new Booking(date, serviceType, Status.PENDING, selectedVendor, loggedInUser));
    System.out.println(result);
  }
}

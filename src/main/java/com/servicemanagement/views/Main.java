package com.servicemanagement.views;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    System.out.println(loggedInUser.toString());
    // createBooking(bookingService, loggedInUser);
    // displayAllBookings(bookingService);
    // displayBookingsByCustomer(bookingService, 1000002);
    // displayBookingsByServiceType(bookingService, ServiceType.AIR_CONDITIONER);
    // updateBookingDate(bookingService, loggedInUser);
    // System.out.println(loggedInUser.toString());
  }

  public static void displayServiceTypeOptions() {
    System.out.println("Press 1 for AC Service");
    System.out.println("Press 2 for Refrigerator Service");
    System.out.println("Press 3 for TV Service");
    System.out.println("Press 4 for Washing Machine Service");
  }

  public static void createBooking(BookingService bookingService, User loggedInUser) {
    // Display service type options
    displayServiceTypeOptions();
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

    // Display vendor options for the selected service type
    System.out.println("Press the corresponding number to choose a vendor");
    List<Vendor> vendors = bookingService.getVendorsByServiceType(serviceType);
    for (int i = 0; i < vendors.size(); i++) {
      System.out.println(i + 1 + " | " + vendors.get(i).getName() + " | " + vendors.get(i).getPrice());
    }
    int selectedVendorNumber = sc.nextInt();
    if (selectedVendorNumber > vendors.size() || selectedVendorNumber <= 0) {
      System.out.println("Sorry! You have not picked a valid vendor.");
      return;
    }
    Vendor selectedVendor = vendors.get(selectedVendorNumber - 1);

    // Prompt user to enter date
    System.out.println("Enter date: (Format YYYY-MM-DD)");
    sc.nextLine();
    String dateInput = sc.nextLine();
    LocalDate date = LocalDate.parse(dateInput);
    LocalDate currentDate = LocalDate.now();
    if (date.isBefore(currentDate) || date.isEqual(currentDate)) {
      System.out.println("Please Enter a date after today.");
      return;
    }

    // Create booking using BookingService
    int result = bookingService
        .createBooking(new Booking(date, serviceType, Status.PENDING, selectedVendor, loggedInUser));
    if (result == 0)
      System.out.println("Something went wrong Please try again.");
    else
      System.out.println("Booking Successful");
  }

  public static void displayAllBookings(BookingService bookingService) {
    List<Booking> bookings = bookingService.getAllBookings();
    System.out.println("ID | Date | Service Type | Vendor Name | Price | Customer Name | Mobile | Address");
    for (Booking booking : bookings) {
      StringBuilder sb = new StringBuilder();
      sb.append(booking.getId()).append(" | ");
      sb.append(booking.getDate()).append(" | ");
      sb.append(booking.getServiceType()).append(" | ");
      sb.append(booking.getVendor().getName()).append(" | ");
      sb.append(booking.getVendor().getPrice()).append(" | ");
      sb.append(booking.getCustomer().getName()).append(" | ");
      sb.append(booking.getCustomer().getMobile()).append(" | ");
      sb.append(booking.getCustomer().getAddress());

      System.out.println(sb.toString());
    }
  }

  public static void displayBookingsByCustomer(BookingService bookingService, long customerId) {
    List<Booking> bookings = bookingService.getBookingsByCustomer(customerId);
    System.out.println("ID | Date | Service Type | Vendor Name | Price");
    for (Booking booking : bookings) {
      StringBuilder sb = new StringBuilder();
      sb.append(booking.getId()).append(" | ");
      sb.append(booking.getDate()).append(" | ");
      sb.append(booking.getServiceType()).append(" | ");
      sb.append(booking.getVendor().getName()).append(" | ");
      sb.append(booking.getVendor().getPrice());

      System.out.println(sb.toString());
    }
  }

  public static void displayBookingsByServiceType(BookingService bookingService, ServiceType serviceType) {
    List<Booking> bookings = bookingService.getBookingsByServiceType(serviceType);
    System.out.println("ID | Date | Service Type | Vendor Name | Price | Customer Name | Mobile | Address");
    for (Booking booking : bookings) {
      StringBuilder sb = new StringBuilder();
      sb.append(booking.getId()).append(" | ");
      sb.append(booking.getDate()).append(" | ");
      sb.append(booking.getServiceType()).append(" | ");
      sb.append(booking.getVendor().getName()).append(" | ");
      sb.append(booking.getVendor().getPrice()).append(" | ");
      sb.append(booking.getCustomer().getName()).append(" | ");
      sb.append(booking.getCustomer().getMobile()).append(" | ");
      sb.append(booking.getCustomer().getAddress());

      System.out.println(sb.toString());
    }
  }

  public static void updateBookingDate(BookingService bookingService, User loggedInUser) {
    System.out.println("Press the corresponding number to update date for a service");

    List<Booking> bookings = bookingService.getBookingsByCustomer(loggedInUser.getId());
    for (int i = 0; i < bookings.size(); i++) {
      System.out.println(i + 1 + " | " + bookings.get(i).getDate() + " | "
          + bookings.get(i).getServiceType() + " | " + bookings.get(i).getStatus());
    }

    int bookingToUpdateNumber = sc.nextInt();
    if (bookingToUpdateNumber > bookings.size() || bookingToUpdateNumber <= 0) {
      System.out.println("Sorry! You didn't pick a Valid Booking");
      return;
    }
    sc.nextLine();
    Booking bookingToUpdate = bookings.get(bookingToUpdateNumber - 1);

    if (bookingToUpdate.getStatus() == Status.CANCELLED || bookingToUpdate.getStatus() == Status.COMPLETED) {
      System.out.println("You can't update date for this booking.");
      return;
    }

    LocalDate today = LocalDate.now();
    if (bookingToUpdate.getDate().isEqual(today) || bookingToUpdate.getDate().isBefore(today)) {
      System.out.println("You can't update date for this booking.");
      return;
    }

    System.out.println("Enter new Date");
    try {
      String dateInput = sc.nextLine();
      LocalDate newDate = LocalDate.parse(dateInput);

      if (newDate.isBefore(today) || newDate.isEqual(today)) {
        System.out.println("Enter a date in the future");
        return;
      }

      if (newDate.isBefore(bookingToUpdate.getDate())) {
        System.out.println("Sorry! You can't Prepone a booking.");
        return;
      }

      int result = bookingService.updateBookingDate(bookingToUpdate.getId(), dateInput);
      if (result == 0)
        System.out.println("Something Went Wrong Please try again.");
      else
        System.out.println("Successfully Updated Booking Date");
    } catch (DateTimeParseException e) {
      e.printStackTrace();
      return;
    }

  }
}

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

    User loggedInUser = null;

    printOptions();
    int choice = sc.nextInt();
    while (true) {
      sc.nextLine();
      switch (choice) {
        case 1:
          addCustomer(userService);
          break;
        case 2:
          loggedInUser = login(userService);
          if (loggedInUser == null)
            System.out.println("Invalid Credentials");
          else {
            if (loggedInUser.isAdmin()) {
              System.out.println("Logged in as " + loggedInUser.getName());
              boolean isAdminLoggedIn = true;
              printAdminOptions();

              while (isAdminLoggedIn) {
                int adminChoice = sc.nextInt();
                sc.nextLine();
                switch (adminChoice) {
                  case 1:
                    displayAllBookings(bookingService);
                    break;
                  case 2:
                    System.out.println("Enter Customer Mobile");
                    String mobile = sc.nextLine();
                    User customerWithGivenMobile = userService.getCustomerByMobile(mobile);
                    if (customerWithGivenMobile == null)
                      System.out.println("No Record Found");
                    else
                      System.out.println(customerWithGivenMobile.toString());
                    break;
                  case 3:
                    System.out.println("Enter Customer Id");
                    long customerId = sc.nextLong();
                    User customerWithGivenId = userService.getCustomerById(customerId);
                    if (customerWithGivenId == null)
                      System.out.println("No Record Found");
                    else
                      System.out.println(customerWithGivenId.toString());
                    break;
                  case 4:
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
                      break;
                    }
                    displayBookingsByServiceType(bookingService, serviceType);
                    break;
                  case 5:
                    System.out.println("Enter Customer Id");
                    long customerIdToGetBookings = sc.nextLong();
                    displayBookingsByCustomer(bookingService, customerIdToGetBookings);
                    break;
                  case 0:
                    isAdminLoggedIn = false;
                    break;
                  default:
                    System.out.println("Please enter a valid choice");
                    break;
                }
                if (isAdminLoggedIn) {
                  printAdminOptions();
                }
              }
            } else if (!loggedInUser.isAdmin()) {
              System.out.println("Logged in as " + loggedInUser.getName());
              boolean isCustomerLoggedIn = true;
              printCustomerOptions();

              while (isCustomerLoggedIn) {
                int customerChoice = sc.nextInt();
                sc.nextLine();
                switch (customerChoice) {
                  case 1:
                    createBooking(bookingService, loggedInUser);
                    break;
                  case 2:
                    displayBookingsByCustomer(bookingService, loggedInUser.getId());
                    break;
                  case 3:
                    cancelBooking(bookingService, loggedInUser);
                    break;
                  case 4:
                    updateBookingDate(bookingService, loggedInUser);
                    break;
                  case 5:
                    updateBookingVendor(bookingService, loggedInUser);
                    break;
                  case 6:
                    User user = userService.getCustomerById(loggedInUser.getId());
                    if (user == null) {
                      System.out.println("Unable to get Profile Details. Please try later");
                    } else {
                      System.out.println(user);
                    }
                    break;
                  case 7:
                    updateProfile(userService, loggedInUser);
                    break;
                  case 0:
                    isCustomerLoggedIn = false;
                    break;
                  default:
                    System.out.println("Please enter a valid choice");
                    break;
                }
                if (isCustomerLoggedIn)
                  printCustomerOptions();
              }
            }
          }
          break;
        case 0:
          loggedInUser = null;
          System.out.println("Thankyou for using Service Management System");
          System.exit(1);
        default:
          System.out.println("Please Enter a valid Option");
          break;
      }
      printOptions();
      choice = sc.nextInt();
    }
  }

  public static void printOptions() {
    System.out.println("----------------------------------------");
    System.out.println("Press 1 to Register");
    System.out.println("Press 2 to Login");
    System.out.println("Press 0 to Exit");
    System.out.println("----------------------------------------");
  }

  public static void printAdminOptions() {
    System.out.println("----------------------------------------");
    System.out.println("Press 1 to View All Bookings");
    System.out.println("Press 2 to Search customer by Mobile Number");
    System.out.println("Press 3 to Search customer by Id");
    System.out.println("Press 4 to Search bookings by Service Type");
    System.out.println("Press 0 to logout");
    System.out.println("----------------------------------------");
  }

  public static void printCustomerOptions() {
    System.out.println("----------------------------------------");
    System.out.println("Press 1 to Create a Booking");
    System.out.println("Press 2 to view all my Bookings");
    System.out.println("Press 3 to Cancel a Booking");
    System.out.println("Press 4 to Change date for a Booking");
    System.out.println("Press 5 to Change vendor for a Booking");
    System.out.println("Press 6 to View my Profile");
    System.out.println("Press 7 to Update my Profile");
    System.out.println("Press 0 to logout");
    System.out.println("----------------------------------------");
  }

  public static void addCustomer(UserService userService) {
    System.out.println("Enter your name");
    String name = sc.nextLine();
    System.out.println("Enter your Email");
    String emailId = sc.nextLine();
    System.out.println("Enter your Mobile Number");
    String mobile = sc.nextLine();
    System.out.println("Enter your Address");
    String address = sc.nextLine();
    System.out.println("Enter your Password");
    String password = sc.nextLine();

    int result = userService.addCustomer(new User(name, emailId, mobile, password, address));
    if (result == 0)
      System.out.println("Something went wrong Please try again.");
    else
      System.out.println("Customer registered successfully.");
  }

  public static User login(UserService userService) {
    System.out.println("Enter your Email");
    String emailId = sc.nextLine();
    System.out.println("Enter your Password");
    String password = sc.nextLine();

    return userService.login(emailId, password);
  }

  public static void updateProfile(UserService userService, User loggedInUser) {
    System.out.println("Press 1 to Update Name");
    System.out.println("Press 2 to Update Email");
    System.out.println("Press 3 to Update Mobile");
    System.out.println("Press 4 to Update Address");

    int fieldToUpdateNumber = sc.nextInt();
    sc.nextLine();
    String fieldToUpdate = switch (fieldToUpdateNumber) {
      case 1 -> "name";
      case 2 -> "emailId";
      case 3 -> "mobile";
      case 4 -> "address";
      default -> null;
    };
    if (fieldToUpdate == null) {
      System.out.println("Sorry! You didn't pick the right field");
      return;
    }

    System.out.println("Enter new " + fieldToUpdate);
    String newValue = sc.nextLine();
    int result = userService.updateProfile(fieldToUpdate, newValue, loggedInUser.getId());
    if (result == 0)
      System.out.println("Something went wrong Please try again.");
    else {
      System.out.println("Profile Successfullt updated.");
    }
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
      sb.append(booking.getStatus()).append(" | ");
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
      sb.append(booking.getStatus()).append(" | ");
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

  public static void updateBookingVendor(BookingService bookingService, User loggedInUser) {
    System.out.println("Press the corresponding number to update vendor for a service");

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

    System.out.println("Press the corresponding number to choose a vendor");
    List<Vendor> vendors = bookingService.getVendorsByServiceType(bookingToUpdate.getServiceType());
    for (int i = 0; i < vendors.size(); i++) {
      System.out.println(i + 1 + " | " + vendors.get(i).getName() + " | " + vendors.get(i).getPrice());
    }
    int selectedVendorNumber = sc.nextInt();
    if (selectedVendorNumber > vendors.size() || selectedVendorNumber <= 0) {
      System.out.println("Sorry! You have not picked a valid vendor.");
      return;
    }
    Vendor selectedVendor = vendors.get(selectedVendorNumber - 1);

    if (selectedVendor.getName().equals(bookingToUpdate.getVendor().getName())) {
      System.out.println("This is the already chosen vendor.");
      return;
    }

    int result = bookingService.updateBookingVendor(bookingToUpdate.getId(), selectedVendor);
    if (result == 0)
      System.out.println("Something went wrong Please try again.");
    else
      System.out.println("Vendor Successfully Updated");
  }

  public static void cancelBooking(BookingService bookingService, User loggedInUser) {
    System.out.println("Press the corresponding number to cancel the service");

    List<Booking> bookings = bookingService.getBookingsByCustomer(loggedInUser.getId());
    for (int i = 0; i < bookings.size(); i++) {
      System.out.println(i + 1 + " | " + bookings.get(i).getDate() + " | "
          + bookings.get(i).getServiceType() + " | " + bookings.get(i).getStatus());
    }

    int bookingToCancelNumber = sc.nextInt();
    if (bookingToCancelNumber > bookings.size() || bookingToCancelNumber <= 0) {
      System.out.println("Sorry! You didn't pick a Valid Booking");
      return;
    }
    Booking bookingToCancel = bookings.get(bookingToCancelNumber - 1);

    if (bookingToCancel.getStatus() == Status.CANCELLED || bookingToCancel.getStatus() == Status.COMPLETED) {
      System.out.println("Sorry! You can't cancel this booking");
      return;
    }

    LocalDate today = LocalDate.now();
    if (bookingToCancel.getDate().isBefore(today)) {
      System.out.println("Sorry! You can't cancel this booking");
      return;
    }

    int result = bookingService.cancelBooking(bookingToCancel.getId());
    if (result == 0)
      System.out.println("Something went wrong Please try again.");
    else
      System.out.println("Booking Cancelled");
  }
}

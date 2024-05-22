package com.servicemanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.services.BookingService;

@WebServlet("/SearchBooking")
public class SearchBooking extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BookingService bookingService = new BookingService();

  public SearchBooking() {
    super();
  }

  // To get single booking by Id.
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String bookingId = (String) request.getParameter("bookingId");

    Booking booking = bookingService.getBookingById(Integer.parseInt(bookingId));

    request.setAttribute("bookingId", bookingId);
    request.setAttribute("booking", booking);
    request.getRequestDispatcher("SearchBookingById.jsp").forward(request, response);
  }

  // To get multiple bookings (By customer id & Service type)
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String searchParameter = (String) request.getParameter("searchParameter");
    try {
      ServiceType serviceType = ServiceType.valueOf(searchParameter.toUpperCase());
      List<Booking> bookings = bookingService.getBookingsByServiceType(serviceType);

      request.setAttribute("bookings", bookings);
      request.setAttribute("serviceType", serviceType.toString());
      request.getRequestDispatcher("ViewBookingsByServiceType.jsp").forward(request, response);
    } catch (IllegalArgumentException e) {
      try {
        long customerId = Long.parseLong(searchParameter);
        List<Booking> bookings = bookingService.getBookingsByCustomer(customerId);

        request.setAttribute("bookings", bookings);
        request.setAttribute("customerId", customerId);
        request.getRequestDispatcher("ViewBookingsByCustomer.jsp").forward(request, response);
      } catch (NumberFormatException ex) {
        System.out.println("Invalid search parameter: " + searchParameter);
      }
    }
  }
}

package com.servicemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servicemanagement.beans.*;
import com.servicemanagement.services.BookingService;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BookingService bookingService = new BookingService();

  public BookingServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session != null) {
      User currentUser = (User) session.getAttribute("user");
      if (currentUser != null) {
        if (!currentUser.isAdmin()) {
          List<Booking> bookings = bookingService.getBookingsByCustomer(currentUser.getId());
          request.setAttribute("bookings", bookings);
          request.getRequestDispatcher("ViewBookings.jsp").forward(request, response);
        } else if (currentUser.isAdmin()) {
          List<Booking> bookings = bookingService.getAllBookings();
          request.setAttribute("bookings", bookings);
          request.getRequestDispatcher("ViewAllBookings.jsp").forward(request, response);
        } else {
          response.sendRedirect("Login.jsp");
        }
      } else {
        response.sendRedirect("Login.jsp");
      }
    } else {
      response.sendRedirect("Login.jsp");
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String serviceTypeString = request.getParameter("serviceType");
    String dateString = request.getParameter("date");
    String vendorIdString = request.getParameter("vendorId");

    Vendor selectedVendor = bookingService.getVendorById(Integer.parseInt(vendorIdString));
    ServiceType serviceType = ServiceType.valueOf(serviceTypeString);
    LocalDate date = LocalDate.parse(dateString);
    Status status = Status.PENDING;

    HttpSession session = request.getSession(false);
    User currentUser = (User) session.getAttribute("user");

    if (currentUser == null) {
      response.sendRedirect("Login.jsp");
    }

    Booking newBooking = new Booking();
    newBooking.setCustomer(currentUser);
    newBooking.setDate(date);
    newBooking.setServiceType(serviceType);
    newBooking.setStatus(status);
    newBooking.setVendor(selectedVendor);

    int result = bookingService.createBooking(newBooking);
    if (result != 0) {
      doGet(request, response);
    } else {
      request.setAttribute("errorMessage", "Cannot book a service right now. Please try again later.");
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}

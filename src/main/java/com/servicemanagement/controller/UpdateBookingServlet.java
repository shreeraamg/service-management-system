package com.servicemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.Vendor;
import com.servicemanagement.services.BookingService;

@WebServlet("/UpdateBookingServlet")
public class UpdateBookingServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BookingService bookingService = new BookingService();

  public UpdateBookingServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String bookingId = request.getParameter("bookingId");
    Booking booking = bookingService.getBookingById(Integer.parseInt(bookingId));
    List<Vendor> vendors = bookingService.getVendorsByServiceType(booking.getServiceType());

    request.setAttribute("booking", booking);
    request.setAttribute("vendors", vendors);
    request.getRequestDispatcher("UpdateBooking.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String bookingId = request.getParameter("bookingId");
    String dateString = request.getParameter("date");
    String vendorIdString = request.getParameter("vendorId");

    int result = bookingService.updateBooking(Integer.parseInt(bookingId), LocalDate.parse(dateString),
        Integer.parseInt(vendorIdString));
    if (result != 0) {
      response.sendRedirect("BookingServlet");
    } else {
      request.setAttribute("errorMessage", "Booking cancellation failed. Please try again later.");
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}

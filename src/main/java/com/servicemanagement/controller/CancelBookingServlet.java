package com.servicemanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servicemanagement.services.BookingService;

@WebServlet("/CancelBookingServlet")
public class CancelBookingServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BookingService bookingService = new BookingService();

  public CancelBookingServlet() {
    super();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String bookingId = request.getParameter("bookingId");

    int result = bookingService.cancelBooking(Integer.parseInt(bookingId));

    if (result != 0) {
      response.sendRedirect("BookingServlet");
    } else {
      request.setAttribute("errorMessage", "Booking cancellation failed. Please try again later.");
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}

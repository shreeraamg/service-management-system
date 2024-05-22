package com.servicemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.beans.Vendor;
import com.servicemanagement.services.BookingService;

@WebServlet("/SelectVendorServlet")
public class SelectVendorServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private BookingService bookingService = new BookingService();

  public SelectVendorServlet() {
    super();
  }

  // Get All Vendors
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Vendor> vendors = bookingService.getAllVendors();

    request.setAttribute("vendors", vendors);
    request.getRequestDispatcher("ViewAllVendors.jsp").forward(request, response);
  }

  // Get Vendors by Service Type
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String serviceTypeString = request.getParameter("serviceType");
    String dateString = request.getParameter("date");

    LocalDate today = LocalDate.now();
    LocalDate bookingDate = LocalDate.parse(dateString);

    if (bookingDate.isBefore(today)) {
      request.setAttribute("errorMessage", "You cannot book a service in the past.");
    }

    ServiceType serviceType = ServiceType.valueOf(serviceTypeString);
    List<Vendor> vendors = bookingService.getVendorsByServiceType(serviceType);

    request.setAttribute("serviceTypeString", serviceTypeString);
    request.setAttribute("dateString", dateString);
    request.setAttribute("vendors", vendors);

    request.getRequestDispatcher("SelectVendor.jsp").forward(request, response);
  }
}

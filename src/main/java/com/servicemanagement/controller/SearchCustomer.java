package com.servicemanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servicemanagement.beans.User;
import com.servicemanagement.services.UserService;

@WebServlet("/SearchCustomer")
public class SearchCustomer extends HttpServlet {
  private static final long serialVersionUID = 1L;
  UserService userService = new UserService();

  public SearchCustomer() {
    super();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    User currentUser = (User) session.getAttribute("user");
    if (currentUser == null || !currentUser.isAdmin()) {
      response.sendRedirect("Login.jsp");
    }

    String customerDetails = (String) request.getParameter("customerDetails");

    if (customerDetails.length() == 10) {
      User customerWithGivenMobile = userService.getCustomerByMobile(customerDetails);

      if (customerWithGivenMobile != null) {
        if (customerWithGivenMobile.isAdmin())
          customerWithGivenMobile = null;
      }

      request.setAttribute("customerMobile", customerDetails);
      request.setAttribute("customerWithGivenMobile", customerWithGivenMobile);
      request.getRequestDispatcher("SearchByMobile.jsp").forward(request, response);
    } else if (customerDetails.length() == 7) {
      long customerId = Long.parseLong(customerDetails);
      User customerWithGivenId = userService.getCustomerById(customerId);

      if (customerWithGivenId != null) {
        if (customerWithGivenId.isAdmin())
          customerWithGivenId = null;
      }

      request.setAttribute("customerId", customerId);
      request.setAttribute("customerWithGivenId", customerWithGivenId);
      request.getRequestDispatcher("SearchById.jsp").forward(request, response);
    } else {
      request.setAttribute("errorMessage", "Please Enter a Valid Customer Id");
      request.getRequestDispatcher("AdminErrorPage.jsp").forward(request, response);
    }
  }
}

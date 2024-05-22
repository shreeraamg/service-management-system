package com.servicemanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servicemanagement.beans.User;
import com.servicemanagement.services.UserService;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  UserService userService = new UserService();

  public RegisterServlet() {
    super();
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String name = req.getParameter("name");
    String email = req.getParameter("email");
    String mobile = req.getParameter("mobile");
    String password = req.getParameter("password");
    String address = req.getParameter("address");

    User user = new User(name, email, mobile, password, address);

    int result = userService.addCustomer(user);

    if (result != 0) {
      res.sendRedirect("Login.jsp");
    } else {
      req.setAttribute("errorMessage", "Something went wrong. Please try later.");
      req.getRequestDispatcher("Register.jsp").include(req, res);
    }
  }
}

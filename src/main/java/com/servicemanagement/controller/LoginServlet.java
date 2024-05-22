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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  UserService userService = new UserService();

  public LoginServlet() {
    super();
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");

    User loggedInUser = userService.login(email, password);
    if (loggedInUser != null) {
      HttpSession session = req.getSession();
      session.setAttribute("user", loggedInUser);
      if (loggedInUser.isAdmin())
        res.sendRedirect("AdminDashboard.jsp");
      else
        res.sendRedirect("index.jsp");
    } else {
      req.setAttribute("errorMessage", "Invalid email or password");
      req.getRequestDispatcher("Login.jsp").include(req, res);
    }
  }
}

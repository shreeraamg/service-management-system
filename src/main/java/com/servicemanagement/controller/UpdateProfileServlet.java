package com.servicemanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.User;
import com.servicemanagement.services.UserService;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserService();
       
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String userId = request.getParameter("userId");
	    User user = userService.getCustomerById(Long.parseLong(userId));
	    
	    request.setAttribute("user", user);
	    request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String userId = req.getParameter("userId");
	    String name = req.getParameter("name");
	    String email = req.getParameter("email");
	    String mobile = req.getParameter("mobile");
	    String password = req.getParameter("password");
	    String address = req.getParameter("address");
	    
	    User updatedUser = new User();
	    updatedUser.setId(Long.parseLong(userId));
	    updatedUser.setName(name);
	    updatedUser.setEmailId(email);
	    updatedUser.setMobile(mobile);
	    updatedUser.setAddress(address);
	    updatedUser.setPassword(password);
	    
	    int result = userService.updateProfile(updatedUser);
	    
	    if(result != 0) {
	    	HttpSession session = req.getSession(false);
	    	updatedUser.setPassword("");
	    	session.setAttribute("user", updatedUser);
	    } else {
	    	req.setAttribute("errorMessage", "Something went wrong. Please try again.");
	    	req.getRequestDispatcher("UpdateProfile.jsp").include(req, res);
	    }
	}

}

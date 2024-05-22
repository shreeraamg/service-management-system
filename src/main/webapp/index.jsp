<%@page import="com.servicemanagement.beans.Booking" %> <%@page
import="java.util.List, com.servicemanagement.beans.*" %> <%@ page
language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Service Management System</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="home-page">
      <% if (request.getSession(false) !=null &&
      request.getSession(false).getAttribute("user") !=null) { %>
      <a href="CreateBooking.jsp" class="btn primary-btn">Create Booking</a>
      <form action="BookingServlet">
        <button type="submit" class="btn secondary-btn">
          View My Bookings
        </button>
      </form>
      <% } else { %>
      <h2>Welcome to Service Management</h2>
      <% } %>
    </div>
  </body>
</html>

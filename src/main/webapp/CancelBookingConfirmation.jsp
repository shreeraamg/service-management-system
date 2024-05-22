<%@page import="com.servicemanagement.beans.Booking"%> <%@page
import="java.util.List, com.servicemanagement.beans.*"%> <%@ page
language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Delete Booking</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="flex-container">
      <% String bookingId = request.getParameter("bookingId"); %>
      <div class="modal">
        <h2>Cancel Booking <%= bookingId %></h2>
        <p>
          Are you sure want to cancel this booking?<br />This action cannot be
          undone.
        </p>
        <span>
          <form action="BookingServlet">
            <button type="submit" class="btn secondary-btn">Back</button>
          </form>
          <form action="CancelBookingServlet" method="post">
            <input type="hidden" name="bookingId" value="<%= bookingId %>" />
            <button type="submit" class="btn danger-btn">Cancel</button>
          </form>
        </span>
      </div>
    </div>
  </body>
</html>

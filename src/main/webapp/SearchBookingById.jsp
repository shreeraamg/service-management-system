<%@page import="com.servicemanagement.beans.Booking"%> <%@ page language="java"
contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>SMS - Admin</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="admin-main">
      <%@ include file="../components/sidebar.jsp" %>
      <main class="main-content">
        <div class="admin-container">
          <h1>Search Booking by Id</h1>
          <form action="<%= request.getContextPath() %>/SearchBooking">
            <input
              name="bookingId"
              id="bookingId"
              type="text"
              placeholder="Enter Booking Id here.."
            />
            <button class="btn primary-btn">Search</button>
          </form>
          <div class="customer-details">
            <% Booking booking = (Booking) request.getAttribute("booking");
            String bookingId = (String) request.getAttribute("bookingId");
            if(bookingId != null) { if(booking != null) { %>
            <table>
              <tbody>
                <tr>
                  <th style="width: 30%">Booking Id</th>
                  <td><%= booking.getId() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Service Date</th>
                  <td><%= booking.getDate().toString() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Service Type</th>
                  <td><%= booking.getServiceType() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Status</th>
                  <td><%= booking.getStatus() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Vendor Name</th>
                  <td><%= booking.getVendor().getName() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Price</th>
                  <td><%= booking.getVendor().getPrice() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Customer Name</th>
                  <td><%= booking.getCustomer().getName() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Customer Mobile</th>
                  <td><%= booking.getCustomer().getMobile() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Customer Address</th>
                  <td><%= booking.getCustomer().getAddress() %></td>
                </tr>
              </tbody>
            </table>
            <% } else { %>
            <p>No Booking with given Id.</p>
            <% } } else { %>
            <p style="display: hidden"></p>
            <% } %>
          </div>
        </div>
      </main>
    </div>
  </body>
</html>

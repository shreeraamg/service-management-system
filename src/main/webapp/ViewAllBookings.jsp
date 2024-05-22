<%@page import="com.servicemanagement.beans.Booking"%>
<%@page import="com.servicemanagement.beans.Vendor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>SMS - Admin</title>
		<link rel="stylesheet" href="styles/style.css" />
	</head>
	<body>
		<%@ include file="../components/header.jsp" %>
		<div class="admin-main">
    	<%@ include file="../components/sidebar.jsp" %>
    	<main class="main-content">
    		<div class="admin-container">
    			<h1 class="admin-heading">All Bookings</h1>
    			<div class="vendor-details">
    				<table>
              <thead>
                <tr>
                  <th>Booking ID</th>
                  <th>Date</th>
                  <th>Service Type</th>
                  <th>Vendor Name</th>
                  <th>Customer Name</th>
                  <th>Customer Mobile</th>
                  <th>Status</th>
                </tr>
              </thead>
    					<tbody>
    					  <% 
    					  List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    				  	if(bookings != null || !bookings.isEmpty()) {
    						  for(Booking b : bookings) {
    					  %>
                <tr>
                  <td><%= b.getId() %></td>
                  <td><%= b.getDate() %></td>
                  <td><%= b.getServiceType() %></td>
                  <td><%= b.getVendor().getName() %></td>
                  <td><%= b.getCustomer().getName() %></td>
                  <td><%= b.getCustomer().getMobile() %></td>
                  <td><%= b.getStatus() %></td>
                </tr>
    					  <% } } %>
    					</tbody>
						</table>	
    			</div>
    		</div>
    	</main>
  	</div>
	</body>
</html>
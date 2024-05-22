<%@page import="com.servicemanagement.beans.Booking"%>
<%@page import="java.util.List, com.servicemanagement.beans.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>My Bookings</title>
		<link rel="stylesheet" href="styles/style.css" />
	</head>
	<body>
		<%@ include file="../components/header.jsp" %>
		<div class="container">
    		<% if (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null) { %>
    		<h2 style="padding: 10px; text-align: center;">Your Bookings</h2>
    		<table style="width: 90%; margin: 0 auto;">
        <thead>
            <tr>
                <th style="width: 10%;">Booking ID</th>
                <th style="width: 15%;">Date</th>
                <th style="width: 20%;">Service Type</th>
                <th style="width: 20%;">Vendor Name</th>
                <th style="width: 10%;">Price</th>
                <th style="width: 10%;">Status</th>
                <th style="width: 15%;">Actions</th>
            </tr>
        </thead>
        <tbody>
            <% List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
            if(bookings != null && !bookings.isEmpty()) {
            for(Booking b :  bookings) {
            	boolean isPending = b.getStatus() == Status.PENDING;
            %>
            <tr>
                <td style="width: 10%;"><%= b.getId() %></td>
                <td style="width: 15%;"><%= b.getDate() %></td>
                <td style="width: 20%;"><%= b.getServiceType() %></td>
                <td style="width: 20%;"><%= b.getVendor().getName() %></td>
                <td style="width: 10%;"><%= b.getVendor().getPrice() %></td>
                <td style="width: 10%;"><%= b.getStatus() %></td>
                <td style="width: 15%;" class="action-column">
                    <form action="UpdateBookingServlet">
                    	<input type="hidden" name="bookingId" value="<%= b.getId() %>" />
                    	<button 
                    		class="btn edit" 
                    		type="submit" 
                    		<%= !isPending ? "disabled" : "" %>
                    	>
                    		Edit
                    	</button>
                    </form>
                    <form action="<%= request.getContextPath() %>/CancelBookingConfirmation.jsp" method="post">
                    	<input type="hidden" name="bookingId" value="<%= b.getId() %>" />
                    	<button 
                    		class="btn danger-btn" 
                    		type="submit" 
                    		<%= !isPending ? "disabled" : "" %>
                    	>
                    		Cancel
                    	</button>
                    </form>
                </td>
            </tr>
            <% }
            } else { %>
            <tr>
                <td colspan="2">No Bookings Found</td>
            </tr>
            <% }
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>
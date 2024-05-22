<%@page import="com.servicemanagement.beans.Booking"%>
<%@page import="java.util.List, com.servicemanagement.beans.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="ISO-8859-1">
		<title>Service Management System</title>
		<link rel="stylesheet" href="styles/style.css" />
	</head>
	<body>
		<%@ include file="../components/header.jsp" %>
		<div class="flex-container">
    		<%
    		List<Vendor> vendors = (List<Vendor>) request.getAttribute("vendors");
    		Booking booking = (Booking) request.getAttribute("booking");
    		%>
    		<form action="<%= request.getContextPath() %>/UpdateBookingServlet" method="post" class="form-container" style="width: 40%;">
    			<input type="hidden" name="bookingId" value="<%= booking.getId() %>" />
    			<div class="input-element">
	    			<label for="serviceType">Select Service Type</label>
					<input name="serviceType" id="serviceType" value="<%= booking.getServiceType().toString() %>" readonly="readonly" />
    			</div>
    			<div class="input-element">
    				<label for="date">Service Date</label>
    				<input type="date" name="date" id="date" value="<%= booking.getDate().toString() %>" min="<%= booking.getDate().toString() %>" />
    			</div>
    			<label>Choose Vendor</label>
				<table>
					<thead>
						<tr>
							<th></th>
							<th>Vendor Name</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<% for (Vendor vendor : vendors) { %>
						<tr>
				            <td>
								<div class="vendor-radio">
                  					<input type="radio" name="vendorId" id="<%= vendor.getId() %>" value="<%= vendor.getId() %>" <% if (vendor.getId() == booking.getVendor().getId()) { %> checked="checked" <% } %>>
                				</div>
              				</td>
              				<td><%= vendor.getName() %></td>
				            <td><%= vendor.getPrice() %></td>
            			</tr>
          				<% } %>
        			</tbody>
     			</table>
     			<div class="form-action">
     				<form action="BookingServlet">
						<button type="submit" class="btn secondary-btn">Back</button>
					</form>
     				<button class="btn primary-btn">Update Booking</button>
     			</div>
    		</form>
  		</div>
	</body>
</html>
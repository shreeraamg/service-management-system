<%@page import="com.servicemanagement.beans.Booking"%>
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
    				<h1>View Bookings by ServiceType</h1>
    				<form action="<%= request.getContextPath() %>/SearchBooking" method="post">
						<select name="searchParameter" id="searchParameter" required="required">
							<option value="">Select Service Type</option>
							<option value="AIR_CONDITIONER">Air Conditioner</option>
							<option value="REFRIGERATOR">Refrigerator</option>
							<option value="TELEVISION">Television</option>
							<option value="WASHING_MACHINE">Washing Machine</option>
						</select>
    					<button class="btn primary-btn">Search</button>
    				</form>
    				<div>
    					<% 
    					List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    					String serviceType = (String) request.getAttribute("serviceType");
    					if(serviceType != null) {
    						if(bookings != null || !bookings.isEmpty()) {
    					%>
    						<table>
        						<thead>
            						<tr>
						                <th>Booking ID</th>
						                <th>Date</th>
						                <th>Vendor Name</th>
						                <th>Customer Name</th>
						                <th>Customer Mobile</th>
						                <th>Status</th>
            						</tr>
        						</thead>
        						<tbody>
        							<% for(Booking b : bookings) { %>
        								<tr>
                							<td><%= b.getId() %></td>
							                <td><%= b.getDate() %></td>
							                <td><%= b.getVendor().getName() %></td>
							                <td><%= b.getCustomer().getName() %></td>
							                <td><%= b.getCustomer().getMobile() %></td>
							                <td><%= b.getStatus() %></td>
							            </tr>
        							<% } %>
        						</tbody>		
    					<% 	} else { %>
    							<p>No Bookings Found with Given Service Type</p>
    					<%  } 
    					} else { %>
    						<p style="display: hidden;"></p>
    					<% } %>    					
    				</div>
    			</div>
    		</main>
  		</div>
	</body>
</html>
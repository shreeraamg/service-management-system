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
    				<h1>View Bookings by Customer Id</h1>
    				<form action="<%= request.getContextPath() %>/SearchBooking" method="post">
    					<input name="searchParameter" id="searchParameter" type="text" placeholder="Enter Customer Id here.." />
    					<button class="btn primary-btn">Search</button>
    				</form>
    				<div>
    					<% 
    					List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
    					Long customerId = (Long) request.getAttribute("customerId");
    					if(customerId != null) {
    						if(bookings != null || !bookings.isEmpty()) {
    					%>
    						<table>
        						<thead>
            						<tr>
						                <th>Booking ID</th>
						                <th>Date</th>
						                <th>Service Type</th>
						                <th>Vendor Name</th>
						                <th>Status</th>
            						</tr>
        						</thead>
        						<tbody>
        							<% for(Booking b : bookings) { %>
        								<tr>
                							<td><%= b.getId() %></td>
							                <td><%= b.getDate() %></td>
							                <td><%= b.getServiceType() %></td>
							                <td><%= b.getVendor().getName() %></td>
							                <td><%= b.getStatus() %></td>
							            </tr>
        							<% } %>
        						</tbody>		
    					<% 	} else { %>
    							<p>This customer has not made any bookings</p>
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
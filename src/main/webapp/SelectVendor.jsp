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
    		String serviceType = (String) request.getAttribute("serviceTypeString");
    		String date = (String) request.getAttribute("dateString");
    		%>
    		<form action="<%= request.getContextPath() %>/BookingServlet" method="post" class="form-container" style="width: 40%;">
    			<div class="input-element">
	    			<label for="serviceType">Select Service Type</label>
					<input name="serviceType" id="serviceType" value="<%= serviceType %>" readonly="readonly" />
    			</div>
    			<div class="input-element">
    				<label for="date">Service Date</label>
    				<input type="date" name="date" id="date" value="<%= date %>" readonly="readonly" />
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
                  					<input required="required" type="radio" name="vendorId" id="<%= vendor.getId() %>" value="<%= vendor.getId() %>">
                				</div>
              				</td>
              				<td><%= vendor.getName() %></td>
				            <td><%= vendor.getPrice() %></td>
            			</tr>
          				<% } %>
        			</tbody>
     			</table>
     			<div class="form-action">
     				<a href="<%= request.getContextPath() %>/CreateBooking.jsp" class="btn secondary-btn">Back</a>
     				<button class="btn primary-btn">Create Booking</button>
     			</div>
    		</form>
  		</div>
	</body>
</html>
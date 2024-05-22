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
    				<h1 class="admin-heading">All Vendors</h1>
    				<div class="vendor-details">
    					<table>
    					<thead>
    						<tr>
    							<th>Id</th>
    							<th>Name</th>
    							<th>Service Type</th>
    							<th>Price</th>
    						</tr>
    					</thead>
    					<tbody>
    					<% 
    					List<Vendor> vendors = (List<Vendor>) request.getAttribute("vendors");
    					if(vendors != null || !vendors.isEmpty()) {
    						for(Vendor v : vendors) {
    					%>
    							<tr>
    								<td><%= v.getId() %></td>
    								<td><%= v.getName() %></td>
    								<td><%= v.getServiceType() %></td>
    								<td><%= v.getPrice() %></td>
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
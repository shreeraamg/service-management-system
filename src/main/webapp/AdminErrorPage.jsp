<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>Error</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="container">
      <h2>An error occurred</h2>
      <p><%= request.getAttribute("errorMessage") %></p>
      <a href="AdminDashboard.jsp">Go back to Dashboard</a>
    </div>
  </body>
</html>

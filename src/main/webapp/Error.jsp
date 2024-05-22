<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Error</title>
    <link rel="stylesheet" href="styles/style.css" />
    <style>
      .error-container {
        height: calc(100vh - 10vh);
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        row-gap: 20px;
      }
      h1 {
        font-size: 3rem;
      }
    </style>
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="error-container">
      <h1>Oops!</h1>
      <p class="error"><%= request.getAttribute("errorMessage") %></p>
      <a class="white-btn btn" href="BookingServlet">Go back to Bookings</a>
    </div>
  </body>
</html>

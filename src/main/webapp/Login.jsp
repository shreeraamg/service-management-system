<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Service Management System</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="flex-container">
      <form
        action="<%= request.getContextPath() %>/LoginServlet"
        method="post"
        class="form-container"
      >
        <h2>Login</h2>
        <div class="input-element">
          <label for="email">Email Id</label>
          <input name="email" id="email" type="email" />
        </div>
        <div class="input-element">
          <label for="password">Password</label>
          <input name="password" id="password" type="password" />
        </div>
        <button type="submit" class="btn primary-btn">Login</button>
        <% String errorMessage = (String) request.getAttribute("errorMessage");
        if(errorMessage != null) { %>
        <p class="error"><%=errorMessage %></p>
        <% } %>
      </form>
    </div>
  </body>
</html>

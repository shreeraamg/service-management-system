<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Update Profile</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="flex-container">
    	<% User user = (User) request.getAttribute("user"); %>
      <form
        action="<%= request.getContextPath() %>/UpdateProfileServlet"
        method="post"
        class="form-container"
      >
        <h2>Register</h2>
        <input type="hidden" name="userId" value="<%= user.getId() %>" />
        <div class="input-element">
          <label for="name">Name</label>
          <input name="name" id="name" type="text" required min="3" value="<%= user.getName() %>" />
        </div>
        <div class="input-element">
          <label for="email">Email Id</label>
          <input name="email" id="email" type="email" required value="<%= user.getEmailId() %>" />
        </div>
        <div class="input-element">
          <label for="mobile">Mobile</label>
          <input
            name="mobile"
            id="mobile"
            type="tel"
            required
            pattern="[0-9]{10}"
            value="<%= user.getMobile() %>"
          />
        </div>
        <div class="input-element">
          <label for="address">Address</label>
          <input name="address" id="address" type="text" required value="<%= user.getAddress() %>" />
        </div>
        <div class="input-element">
          <label for="password">Password</label>
          <input
            name="password"
            id="password"
            type="password"
            required
            min="6"
            placeholder="Enter your new password"
          />
        </div>
        <button type="submit" class="btn primary-btn">Register</button>
        <% String errorMessage = (String) request.getAttribute("errorMessage");
        if(errorMessage != null) { %>
        <p class="error"><%=errorMessage %></p>
        <% } %>
      </form>
    </div>
  </body>
</html>

<%@page import="com.servicemanagement.beans.Booking"%> <%@page
import="java.util.List, com.servicemanagement.beans.*"%> <%@ page
language="java" contentType="text/html; charset=ISO-8859-1"
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
        action="<%= request.getContextPath() %>/SelectVendorServlet"
        method="post"
        class="form-container"
      >
        <div class="input-element">
          <label for="serviceType">Select Service Type</label>
          <select name="serviceType" id="serviceType" required="required">
            <option value="AIR_CONDITIONER">Air Conditioner</option>
            <option value="REFRIGERATOR">Refrigerator</option>
            <option value="TELEVISION">Television</option>
            <option value="WASHING_MACHINE">Washing Machine</option>
          </select>
        </div>
        <div class="input-element">
          <label for="date">Service Date</label>
          <input type="date" name="date" id="date" required="required" />
        </div>
        <button class="btn primary-btn">Select Vendor</button>
      </form>
    </div>
    <script src="scripts/getCurrentDate.js"></script>
  </body>
</html>

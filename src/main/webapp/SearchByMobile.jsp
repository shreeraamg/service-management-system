<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>SMS - Admin</title>
    <link rel="stylesheet" href="styles/style.css" />
  </head>
  <body>
    <%@ include file="../components/header.jsp" %>
    <div class="admin-main">
      <%@ include file="../components/sidebar.jsp" %>
      <main class="main-content">
        <div class="admin-container">
          <h1>Search Customer by Mobile</h1>
          <form
            action="<%= request.getContextPath() %>/SearchCustomer"
            method="post"
          >
            <input
              name="customerDetails"
              id="customerDetails"
              type="text"
              placeholder="Enter Customer Mobile here.."
              pattern="[0-9]{10}"
              title="Please enter a 10-digit mobile number"
              required
            />
            <button class="btn primary-btn">Search</button>
          </form>
          <div class="customer-details">
            <% User customerWithGivenMobile = (User)
            request.getAttribute("customerWithGivenMobile"); String
            customerMobile = (String) request.getAttribute("customerMobile");
            if(customerMobile != null) { if(customerWithGivenMobile != null) {
            %>
            <table>
              <tbody>
                <tr>
                  <th style="width: 30%">Customer Id</th>
                  <td><%= customerWithGivenMobile.getId() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Customer Name</th>
                  <td><%= customerWithGivenMobile.getName() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Email</th>
                  <td><%= customerWithGivenMobile.getEmailId() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Mobile</th>
                  <td><%= customerWithGivenMobile.getMobile() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Address</th>
                  <td><%= customerWithGivenMobile.getAddress() %></td>
                </tr>
              </tbody>
            </table>
            <% } else { %>
            <p>Customer Not Found!!</p>
            <% } } else { %>
            <p style="display: hidden"></p>
            <% } %>
          </div>
        </div>
      </main>
    </div>
  </body>
</html>

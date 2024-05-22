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
          <h1>Search Customer by Id</h1>
          <form
            action="<%= request.getContextPath() %>/SearchCustomer"
            method="post"
          >
            <input
              name="customerDetails"
              id="customerDetails"
              type="text"
              placeholder="Enter Customer Id here.."
            />
            <button class="btn primary-btn">Search</button>
          </form>
          <div class="customer-details">
            <% User customerWithGivenId = (User)
            request.getAttribute("customerWithGivenId"); Long customerId =
            (Long) request.getAttribute("customerId"); if(customerId != null) {
            if(customerWithGivenId != null) { %>
            <table>
              <tbody>
                <tr>
                  <th style="width: 30%">Customer Id</th>
                  <td><%= customerWithGivenId.getId() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Customer Name</th>
                  <td><%= customerWithGivenId.getName() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Email</th>
                  <td><%= customerWithGivenId.getEmailId() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Mobile</th>
                  <td><%= customerWithGivenId.getMobile() %></td>
                </tr>
                <tr>
                  <th style="width: 30%">Address</th>
                  <td><%= customerWithGivenId.getAddress() %></td>
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

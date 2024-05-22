<%@page import="com.servicemanagement.beans.User"%>
<%
session = request.getSession(false);
User loggedInUser = null;
if(session != null) {
	loggedInUser = (User) session.getAttribute("user");
}
%>

<header class="header">
	<a href="<%= request.getContextPath() %>/index.jsp" class="logo">Service Management System</a>
    <nav>
    	<%
    	if(loggedInUser == null) {
    	%>
    		<a href="<%= request.getContextPath() %>/Login.jsp" class="btn primary-btn">Login</a>
    		<a href="<%= request.getContextPath() %>/Register.jsp" class="btn white-btn">Register</a>
    	<% } else { %>
			<% if(loggedInUser.isAdmin()) { %>
				<p><%= loggedInUser.getName() %></p>
			<% } else { %>
				<form action="UpdateProfileServlet">
					<input type="hidden" name="userId" value="<%= loggedInUser.getId() %>" />
					<button class="btn"><%= loggedInUser.getName() %></button>
				</form>
			<% } %>
			<% if(!loggedInUser.isAdmin()) { %> <a href="CreateBooking.jsp" class="btn primary-btn">Create Booking</a> <% } %>
			<a href="<%= request.getContextPath() %>/LogoutServlet" class="btn secondary-btn">Logout</a>
		<% } %>
    </nav>
</header>
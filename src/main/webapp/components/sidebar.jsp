<aside class="sidebar">
	<div class="side-heading-container">
		<h6>Customers</h6>
		<span class="line"></span>
	</div>
	<a href="SearchById.jsp">Search by Id</a>
	<a href="SearchByMobile.jsp">Search by Mobile</a>
	<div class="side-heading-container">
		<h6>Bookings</h6>
		<span class="line"></span>
	</div>
	<a href="SearchBookingById.jsp">Search By Id</a>
	<a href="<%= request.getContextPath() %>/BookingServlet">View All Bookings</a>
	<a href="ViewBookingsByCustomer.jsp">View Bookings by Customer</a>
	<a href="ViewBookingsByServiceType.jsp">View Bookings by Service Type</a>
	<div class="side-heading-container">
		<h6>Vendors</h6>
		<span class="line"></span>
	</div>
	<a href="<%= request.getContextPath() %>/SelectVendorServlet">View All Vendors</a>
</aside>

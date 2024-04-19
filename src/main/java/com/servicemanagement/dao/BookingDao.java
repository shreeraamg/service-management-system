package com.servicemanagement.dao;

import java.util.List;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.beans.Vendor;

public interface BookingDao {
  // Bookings
  public int createBooking(Booking booking);

  public List<Booking> getAllBookings();

  public List<Booking> getBookingsByCustomer(long customerId);

  public List<Booking> getBookingsByServiceType(ServiceType serviceType);

  public int updateBookingDate(int bookingId, String newDate);

  public int updateBookingVendor(int bookingId, Vendor newVendor);

  public int cancelBooking(int bookingId);

  // Vendors
  public List<Vendor> getVendorsByServiceType(ServiceType serviceType);
}

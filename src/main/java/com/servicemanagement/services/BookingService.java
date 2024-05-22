package com.servicemanagement.services;

import java.time.LocalDate;
import java.util.List;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.beans.Vendor;
import com.servicemanagement.dao.BookingDao;
import com.servicemanagement.dao.BookingDaoDatabaseImpl;

public class BookingService {
  private BookingDao bookingDao = new BookingDaoDatabaseImpl();

  public int createBooking(Booking booking) {
    return bookingDao.createBooking(booking);
  }

  public List<Booking> getAllBookings() {
    return bookingDao.getAllBookings();
  }
  
  public Booking getBookingById(int bookingId) {
	  return bookingDao.getBookingById(bookingId);
  }

  public List<Booking> getBookingsByCustomer(long customerId) {
    return bookingDao.getBookingsByCustomer(customerId);
  }

  public List<Booking> getBookingsByServiceType(ServiceType serviceType) {
    return bookingDao.getBookingsByServiceType(serviceType);
  }
  
  public int updateBooking(int bookingId, LocalDate date, int vendorId) {
	  return bookingDao.updateBooking(bookingId, date, vendorId);
  }

  public int updateBookingDate(int bookingId, String newDate) {
    return bookingDao.updateBookingDate(bookingId, newDate);
  }

  public int updateBookingVendor(int bookingId, Vendor newVendor) {
    return bookingDao.updateBookingVendor(bookingId, newVendor);
  }

  public int cancelBooking(int bookingId) {
    return bookingDao.cancelBooking(bookingId);
  }
  
  public List<Vendor> getAllVendors() {
	return bookingDao.getAllVendors();
  }

  public List<Vendor> getVendorsByServiceType(ServiceType serviceType) {
    return bookingDao.getVendorsByServiceType(serviceType);
  }
  
  public Vendor getVendorById(int vendorId) {
	return bookingDao.getVendorById(vendorId);
  }
}

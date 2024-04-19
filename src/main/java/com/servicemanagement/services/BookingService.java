package com.servicemanagement.services;

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

  public List<Booking> getBookingsByCustomer(long customerId) {
    return bookingDao.getBookingsByCustomer(customerId);
  }

  public List<Vendor> getVendorsByServiceType(ServiceType serviceType) {
    return bookingDao.getVendorsByServiceType(serviceType);
  }
}

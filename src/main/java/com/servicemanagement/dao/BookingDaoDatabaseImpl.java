package com.servicemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.servicemanagement.beans.Booking;
import com.servicemanagement.beans.ServiceType;
import com.servicemanagement.beans.Status;
import com.servicemanagement.beans.User;
import com.servicemanagement.beans.Vendor;
import com.servicemanagement.utils.DbConnection;

public class BookingDaoDatabaseImpl implements BookingDao {
  private DbConnection dbConnection = new DbConnection();

  @Override
  public int createBooking(Booking booking) {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "INSERT INTO Booking (serviceType, date, status, vendorId, customerId) VALUES (?, ?, ?, ?, ?);";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, booking.getServiceType().toString());
      statement.setString(2, booking.getDate().toString());
      statement.setString(3, booking.getStatus().toString());
      statement.setInt(4, booking.getVendor().getId());
      statement.setLong(5, booking.getCustomer().getId());

      int result = statement.executeUpdate();

      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      dbConnection.closeConnection(connection);
    }
  }

  @Override
  public List<Booking> getAllBookings() {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "SELECT b.id, b.date, b.serviceType, b.status, p.id, p.name, p.price, u.id, u.name, u.emailId, u.mobile, u.address FROM Booking b JOIN Providers p ON b.vendorId = p.id JOIN User u ON b.customerId = u.id;";

      PreparedStatement statement = connection.prepareStatement(query);

      ResultSet rs = statement.executeQuery();

      List<Booking> bookings = new ArrayList<>();
      while (rs.next()) {
        Booking booking = new Booking();

        Vendor vendor = new Vendor();
        vendor.setId(rs.getInt(5));
        vendor.setName(rs.getString(6));
        vendor.setServiceType(ServiceType.valueOf(rs.getString(3)));
        vendor.setPrice(rs.getInt(7));
        booking.setVendor(vendor);

        User customer = new User();
        customer.setId(rs.getLong(8));
        customer.setName(rs.getString(9));
        customer.setEmailId(rs.getString(10));
        customer.setMobile(rs.getString(11));
        customer.setAddress(rs.getString(12));
        booking.setCustomer(customer);

        booking.setId(rs.getInt(1));
        booking.setDate(LocalDate.parse(rs.getString(2)));
        booking.setServiceType(ServiceType.valueOf(rs.getString(3)));
        booking.setStatus(Status.valueOf(rs.getString(4)));

        bookings.add(booking);
      }

      return bookings;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      dbConnection.closeConnection(connection);
    }
  }

  @Override
  public List<Booking> getBookingsByCustomer(long customerId) {
    throw new UnsupportedOperationException("Unimplemented method 'getBookingsByCustomer'");
  }

  @Override
  public List<Booking> getBookingsByServiceType(ServiceType serviceType) {
    throw new UnsupportedOperationException("Unimplemented method 'getBookingsByServiceType'");
  }

  @Override
  public int updateBookingDate(int bookingId, String newDate) {
    throw new UnsupportedOperationException("Unimplemented method 'updateBookingDate'");
  }

  @Override
  public int updateBookingVendor(int bookingId, Vendor newVendor) {
    throw new UnsupportedOperationException("Unimplemented method 'updateBookingVendor'");
  }

  @Override
  public int cancelBooking(int bookingId) {
    throw new UnsupportedOperationException("Unimplemented method 'cancelBooking'");
  }

  @Override
  public List<Vendor> getVendorsByServiceType(ServiceType serviceType) {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "SELECT * FROM Providers WHERE serviceType = ?;";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, serviceType.toString());

      ResultSet rs = statement.executeQuery();

      List<Vendor> vendors = new ArrayList<>();
      while (rs.next()) {
        Vendor vendor = new Vendor();
        vendor.setId(rs.getInt(1));
        vendor.setName(rs.getString(2));
        vendor.setServiceType(ServiceType.valueOf(rs.getString(3)));
        vendor.setPrice(rs.getInt(4));

        vendors.add(vendor);
      }

      return vendors;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      dbConnection.closeConnection(connection);
    }
  }
}

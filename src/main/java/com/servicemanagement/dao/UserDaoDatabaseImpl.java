package com.servicemanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.servicemanagement.beans.User;
import com.servicemanagement.utils.DbConnection;

public class UserDaoDatabaseImpl implements UserDao {
  private DbConnection dbConnection = new DbConnection();

  @Override
  public String addCustomer(User user) {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "INSERT INTO User (name, emailId, mobile, password, address, isAdmin) VALUES (?, ?, ?, ?, ?, ?);";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, user.getName());
      statement.setString(2, user.getEmailId());
      statement.setString(3, user.getMobile());
      statement.setString(4, user.getPassword());
      statement.setString(5, user.getAddress());
      statement.setInt(6, 0);

      int result = statement.executeUpdate();

      ResultSet rs = statement.getGeneratedKeys();
      long customerId = rs.getLong(1);

      if (result > 0)
        return "Customer Registered Successfully with Id " + customerId;
      else
        return "Failed to retrive customer id";
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      dbConnection.closeConnection(connection);
    }

    return "Something went wrong please try again later";
  }

  @Override
  public User login(String emailId, String password) {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "SELECT * FROM User WHERE emailId = ? AND password = ?";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, emailId);
      statement.setString(2, password);

      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        User loggedInUser = new User();
        loggedInUser.setId(rs.getLong(1));
        loggedInUser.setName((rs.getString(2)));
        loggedInUser.setEmailId((rs.getString(3)));
        loggedInUser.setMobile((rs.getString(4)));
        loggedInUser.setPassword("");
        loggedInUser.setAddress(rs.getString(6));
        loggedInUser.setAdmin(rs.getInt(7) == 1);

        return loggedInUser;
      }

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      dbConnection.closeConnection(connection);
    }
  }

  @Override
  public User getCustomerByMobile(String mobile) {
    throw new UnsupportedOperationException("Unimplemented method 'getCustomerByMobile'");
  }

  @Override
  public User getCustomerById(long id) {
    throw new UnsupportedOperationException("Unimplemented method 'getCustomerById'");
  }

}

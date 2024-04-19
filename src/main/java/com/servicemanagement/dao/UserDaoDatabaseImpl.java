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
  public User loginUser(String emailId, String password) {
    return null;
  }

}

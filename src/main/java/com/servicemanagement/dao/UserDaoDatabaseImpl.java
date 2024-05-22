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
  public int addCustomer(User user) {
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

      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      dbConnection.closeConnection(connection);
    }
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
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "SELECT * FROM User WHERE mobile = ?";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, mobile);

      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        User user = new User();
        user.setId(rs.getLong(1));
        user.setName((rs.getString(2)));
        user.setEmailId((rs.getString(3)));
        user.setMobile((rs.getString(4)));
        user.setPassword("");
        user.setAddress(rs.getString(6));
        user.setAdmin(rs.getInt(7) == 1);

        return user;
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
  public User getCustomerById(long id) {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "SELECT * FROM User WHERE id = ?";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setLong(1, id);

      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        User user = new User();
        user.setId(rs.getLong(1));
        user.setName((rs.getString(2)));
        user.setEmailId((rs.getString(3)));
        user.setMobile((rs.getString(4)));
        user.setPassword("");
        user.setAddress(rs.getString(6));
        user.setAdmin(rs.getInt(7) == 1);

        return user;
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
  public int updateProfile(User user) {
    Connection connection = null;

    try {
      connection = dbConnection.getConnection();
      String query = "UPDATE User SET name = ?, emailId = ?, mobile = ?, address = ?, password = ? WHERE id = ?;";

      PreparedStatement statement = connection.prepareStatement(query);
      statement.setString(1, user.getName());
      statement.setString(2, user.getEmailId());
      statement.setString(3, user.getMobile());
      statement.setString(4, user.getAddress());
      statement.setString(5, user.getPassword());
      statement.setLong(6, user.getId());

      int result = statement.executeUpdate();

      return result;
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      dbConnection.closeConnection(connection);
    }
  }
}

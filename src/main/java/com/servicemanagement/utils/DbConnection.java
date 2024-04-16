package com.servicemanagement.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
  public Connection getConnection() {
    Connection connection = null;

    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager
          .getConnection("jdbc:sqlite:D:\\Learnings\\servicemanagementsystem\\database\\servicemanagement.db");
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}

package com.servicemanagement.dao;

import com.servicemanagement.beans.User;

public interface UserDao {
  public String addCustomer(User user);

  public User loginUser(String emailId, String password);
}

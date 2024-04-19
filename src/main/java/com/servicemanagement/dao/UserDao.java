package com.servicemanagement.dao;

import com.servicemanagement.beans.User;

public interface UserDao {
  public String addCustomer(User user);

  public User login(String emailId, String password);

  public User getCustomerByMobile(String mobile);

  public User getCustomerById(long id);
}

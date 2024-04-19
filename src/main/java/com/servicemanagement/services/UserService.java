package com.servicemanagement.services;

import com.servicemanagement.beans.User;
import com.servicemanagement.dao.UserDao;
import com.servicemanagement.dao.UserDaoDatabaseImpl;

public class UserService {
  private UserDao userDao;

  public UserService() {
    userDao = new UserDaoDatabaseImpl();
  }

  public String addCustomer(User user) {
    return userDao.addCustomer(user);
  }

  public User login(String emailId, String password) {
    return userDao.login(emailId, password);
  }
}

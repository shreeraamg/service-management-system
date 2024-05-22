package com.servicemanagement.services;

import com.servicemanagement.beans.User;
import com.servicemanagement.dao.UserDao;
import com.servicemanagement.dao.UserDaoDatabaseImpl;

public class UserService {
  private UserDao userDao;

  public UserService() {
    userDao = new UserDaoDatabaseImpl();
  }

  public int addCustomer(User user) {
	// Implement Server Side Valiation
    return userDao.addCustomer(user);
  }

  public User login(String emailId, String password) {
    return userDao.login(emailId, password);
  }

  public User getCustomerByMobile(String mobile) {
    return userDao.getCustomerByMobile(mobile);
  }

  public User getCustomerById(long id) {
    return userDao.getCustomerById(id);
  }

  public int updateProfile(User user) {
    return userDao.updateProfile(User user);
  }
}

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

  public int updateProfile(String field, String updatedValue, long id) {
    return userDao.updateProfile(field, updatedValue, id);
  }
}

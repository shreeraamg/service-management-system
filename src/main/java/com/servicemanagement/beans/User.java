package com.servicemanagement.beans;

public class User {
  private long id;
  private String name;
  private String emailId;
  private String mobile;
  private String password;
  private String address;
  private boolean isAdmin;

  public User() {
  }

  public User(String name, String emailId, String mobile, String password, String address) {
    this.name = name;
    this.emailId = emailId;
    this.mobile = mobile;
    this.password = password;
    this.address = address;
  }

  public User(long id, String name, String emailId, String mobile, String password, String address, boolean isAdmin) {
    this(name, emailId, mobile, password, address);
    this.id = id;
    this.isAdmin = isAdmin;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  @Override
  public String toString() {
    // return id + "|" + name + "|" + emailId + "|" + mobile + "|" + address;
    return name + "|" + emailId + "|" + mobile + "|" + password + "|" + address;
  }
}

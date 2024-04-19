package com.servicemanagement.beans;

import java.time.LocalDate;

public class Booking {
  private int id;
  private LocalDate date;
  private ServiceType serviceType;
  private Status status;
  private Vendor vendor;
  private User customer;

  public Booking() {
  }

  public Booking(LocalDate date, ServiceType serviceType, Status status, Vendor vendor, User customer) {
    this.date = date;
    this.serviceType = serviceType;
    this.status = status;
    this.vendor = vendor;
    this.customer = customer;
  }

  public Booking(int id, LocalDate date, ServiceType serviceType, Status status, Vendor vendor, User customer) {
    this(date, serviceType, status, vendor, customer);
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

  public User getCustomer() {
    return customer;
  }

  public void setCustomer(User customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return id + " | " + date + " | " + serviceType + " | " + status + " | " + vendor.getName() + " | "
        + customer.getName() + " | " + customer.getMobile() + " | " + customer.getAddress();
  }
}

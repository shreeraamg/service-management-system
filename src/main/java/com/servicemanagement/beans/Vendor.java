package com.servicemanagement.beans;

public class Vendor {
  private int id;
  private String name;
  private ServiceType serviceType;
  private int price;

  public Vendor() {}

  public Vendor(String name, ServiceType serviceType, int price) {
    this.id = 0;
    this.name = name;
    this.serviceType = serviceType;
    this.price = price;
  }

  public Vendor(int id, String name, ServiceType serviceType, int price) {
    this(name, serviceType, price);
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return id + " | " + name + " | " + serviceType + " | " + price;
  }
}

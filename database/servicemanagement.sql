CREATE TABLE User(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50),
  emailId VARCHAR(255) UNIQUE,
  mobile CHAR(10) UNIQUE,
  password VARCHAR(1024) NOT NULL,
  address VARCHAR(100),
  isAdmin INTEGER
);

-- Admin Data
INSERT INTO User (id, name, emailId, mobile, password, isAdmin)
VALUES (1000000, 'Admin', 'admin@sms.com', '0000000000', 'asdasd', 1);

-- Customer Data (Sample)
INSERT INTO User (name, emailId, mobile, password, address)
VALUES ('Shreeram', 'shreeram@gmail.com', '9080706050', 'asdasd', 'Velachery, Chennai - 91');

-- Vendors Table named as Providers
CREATE TABLE Providers(
  id INTEGER PRIMARY KEY AUTOINCREMENT, 
  name VARCHAR(50), 
  serviceType VARCHAR(50), 
  price INTEGER
);

-- Service Providing Vendors
INSERT INTO Providers (id, name, serviceType, price) VALUES (1, 'LG Service Center', 'WASHING_MACHINE', 1000);
INSERT INTO Providers (name, serviceType, price) VALUES ('Urban Clap', 'WASHING_MACHINE', 800);
INSERT INTO Providers (name, serviceType, price) VALUES ('No Broker', 'REFRIGERATOR', 1200);
INSERT INTO Providers (name, serviceType, price) VALUES ('Samsung Services', 'REFRIGERATOR', 1500);
INSERT INTO Providers (name, serviceType, price) VALUES ('Sony', 'TELEVISION', 2500);
INSERT INTO Providers (name, serviceType, price) VALUES ('Life Good', 'TELEVISION', 3500);
INSERT INTO Providers (name, serviceType, price) VALUES ('Voltas AC Service', 'AIR_CONDITIONER', 600);
INSERT INTO Providers (name, serviceType, price) VALUES ('Blue Star', 'AIR_CONDITIONER', 800);

-- Booking Table
CREATE TABLE Booking(
  id INTEGER PRIMARY KEY AUTOINCREMENT, 
  serviceType VARCHAR(50), 
  date VARCHAR(20), 
  status VARCHAR(50), 
  vendorId INTEGER, 
  customerId INTEGER, 
  FOREIGN KEY (vendorId) REFERENCES Providers(id), 
  FOREIGN KEY (customerId) REFERENCES User(id)
);

-- Booking Data (Sample)
INSERT INTO Booking (id, serviceType, date, status, vendorId, customerId)
VALUES (101, 'AIR_CONDITIONER', '2024-05-02', 'PENDING', 7, 1000001);

-- View All Booking with JOIN operations
SELECT b.id, b.serviceType, b.date, b.status, v.name, u.name, u.mobile, u.address 
FROM Booking b 
JOIN Providers v ON b.vendorId = v.id 
JOIN User u ON b.customerId = u.id;
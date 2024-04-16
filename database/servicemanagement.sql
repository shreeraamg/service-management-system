CREATE TABLE User(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50),
  emailId VARCHAR(255) UNIQUE,
  mobile CHAR(10) UNIQUE,
  password VARCHAR(1024) NOT NULL,
  address VARCHAR(100),
  isAdmin INTEGER
);

INSERT INTO User (id, name, emailId, mobile, password, isAdmin) VALUES (1000000, 'Admin', 'admin@sms.com', '0000000000', 'asdasd', 1);
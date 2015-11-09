
DROP DATABASE IF EXISTS Restaurant;
CREATE DATABASE Restaurant;
USE Restaurant;

DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE (
eid INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
position VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
lastWorked DATE,
PRIMARY KEY (eid));


DROP TABLE IF EXISTS ARC_EMPLOYEE;
CREATE TABLE ARC_EMPLOYEE (
eid INT NOT NULL,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
position VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
lastWorked DATE,
PRIMARY KEY (eid));


DROP TABLE IF EXISTS CUSTOMER ;
CREATE TABLE CUSTOMER (
cid INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
lastVisited DATE NOT NULL,
discount INT NOT NULL DEFAULT 0,
PRIMARY KEY (cid) );


DROP TABLE IF EXISTS ARC_CUSTOMER;
CREATE TABLE ARC_CUSTOMER (
cid INT NOT NULL,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
lastVisited DATE NOT NULL,
PRIMARY KEY (cid) );


DROP TABLE IF EXISTS aTABLE ;
CREATE TABLE aTABLE (
tID INT NOT NULL AUTO_INCREMENT,
eID INT,
seats INT NOT NULL,
available BOOLEAN NOT NULL,
PRIMARY KEY (tID) );

DROP TABLE IF EXISTS MENU;
CREATE TABLE MENU (
mID INT NOT NULL AUTO_INCREMENT,
itemName VARCHAR(25) UNIQUE NOT NULL,
description VARCHAR(100) NOT NULL,
price DECIMAL(10,2) NOT NULL,
type VARCHAR(15) NOT NULL,
PRIMARY KEY (mid) );

DROP TABLE IF EXISTS RATING;
CREATE TABLE RATING (
cID INT NOT NULL,
stars INT,
feedback VARCHAR(140),
PRIMARY KEY (cID),
FOREIGN KEY (cID) REFERENCES CUSTOMER(cID));

DROP TABLE IF EXISTS RECEIPT ;
CREATE TABLE RECEIPT (
rID INT NOT NULL AUTO_INCREMENT,
eID INT NOT NULL ,
cID INT NOT NULL,
subtotal DECIMAL(10,2) NOT NULL,
gratuity DECIMAL(10,2) NOT NULL,
billDate DATE NOT NULL,
PRIMARY KEY (rID) ,
FOREIGN KEY (eID) REFERENCES EMPLOYEE(eID),
FOREIGN KEY (cID) REFERENCES CUSTOMER(cID),
UNIQUE (cID, billDate)
);

DROP TABLE IF EXISTS ARC_RECEIPT ;
CREATE TABLE ARC_RECEIPT (
rID INT NOT NULL,
eID INT NOT NULL ,
cID INT NOT NULL,
subtotal DECIMAL(10,2) NOT NULL,
gratuity DECIMAL(10,2) NOT NULL,
billDate DATE NOT NULL,
PRIMARY KEY (rID) ,
FOREIGN KEY (eID) REFERENCES EMPLOYEE(eID),
FOREIGN KEY (cID) REFERENCES CUSTOMER(cID),
UNIQUE (cID, billDate)
);

DROP TABLE IF EXISTS RECEIPT_ITEM;
CREATE TABLE RECEIPT_ITEM (
rID INT NOT NULL,
mID INT NOT NULL,
quantity INT NOT NULL,
PRIMARY KEY (rID, mID),
FOREIGN KEY (rID) REFERENCES RECEIPT(rID),
FOREIGN KEY (mID) REFERENCES MENU(mID)
);

DROP TABLE IF EXISTS RESERVATION;
CREATE TABLE RESERVATION (
tID INT NOT NULL,
cID INT NOT NULL,
partySize INT NOT NULL,
reservationDate DATE NOT NULL,
FOREIGN KEY (tID) REFERENCES aTABLE(tID), 
FOREIGN KEY (cID) REFERENCES CUSTOMER(cID)
);

/* triggers */
/*Give discounts to customers who give 5-stars ratings*/
DROP TRIGGER IF EXISTS highRater;
CREATE TRIGGER highRater
AFTER INSERT ON Rating
FOR EACH ROW
UPDATE Customer Set discount=10 WHERE cID=new.cID;




/*Update lastVisited when reservation is made*/
DROP TRIGGER IF EXISTS visiting;
CREATE TRIGGER visiting
AFTER INSERT ON Reservation
FOR EACH ROW 
UPDATE Customer Set lastVisited = now() WHERE cID=NEW.cID;


/* Procdedure */
/* Archive receipts */
DROP PROCEDURE IF EXISTS archiveReceipts;
DELIMITER $$
CREATE PROCEDURE archiveReceipts (IN oldDate DATE)
BEGIN
	INSERT INTO arc_Receipt VALUES ( SELECT * FROM Receipt WHERE date<oldDate);
	DELETE FROM Receipt WHERE date<oldDate;
END;
DELIMITER ;

/* archive customers */
DROP PROCEDURE IF EXISTS archiveCustomers;
DELIMITER $$
CREATE PROCEDURE archiveCustomers (IN oldDate DATE)
BEGIN
	INSERT INTO arc_Customer VALUES ( SELECT * FROM Customer WHERE lastVisited<oldDate);
	DELETE FROM Customer WHERE lastVisited<oldDate;
END;
DELIMITER ;

/* archive employees */
DROP PROCEDURE IF EXISTS archiveEmployees;
DELIMITER $$
CREATE PROCEDURE archiveEmployees(IN oldDate DATE)
BEGIN
	INSERT INTO arc_Employee VALUES ( SELECT * FROM Employee WHERE lastWorked<oldDate);
	DELETE FROM Employee WHERE lastWorked<oldDate;
END;
DELIMITER ;


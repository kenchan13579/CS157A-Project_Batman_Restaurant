
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


DROP TABLE IF EXISTS CUSTOMER ;
CREATE TABLE CUSTOMER (
cid INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
discount INT NOT NULL DEFAULT 0,
updatedAt DATETIME NOT NULL DEFAULT "0000-00-00 00:00:00" ON UPDATE NOW(),
PRIMARY KEY (cid) );


DROP TABLE IF EXISTS ARC_CUSTOMER ;
CREATE TABLE ARC_CUSTOMER (
cid INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(25) NOT NULL,
lastName VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
discount INT NOT NULL DEFAULT 0,
updatedAt DATETIME,
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
itemName VARCHAR(50) UNIQUE NOT NULL,
description VARCHAR(140) NOT NULL,
price DECIMAL(10,2) NOT NULL,
type VARCHAR(15) NOT NULL,
PRIMARY KEY (mid) );

DROP TABLE IF EXISTS RATING;
CREATE TABLE RATING (
cID INT NOT NULL,
stars INT,
feedback VARCHAR(140),
PRIMARY KEY (cID));


DROP TABLE IF EXISTS RESERVATION;
CREATE TABLE RESERVATION (
tID INT NOT NULL,
cID INT NOT NULL,
partySize INT NOT NULL,
reservationDate DATE NOT NULL,
PRIMARY KEY (tID, cID, reservationDate),
FOREIGN KEY (tID) REFERENCES aTABLE(tID), 
FOREIGN KEY (cID) REFERENCES CUSTOMER(cID) ON DELETE CASCADE
);

/* triggers */
/*Give discounts to customers who give 5-stars ratings*/
DROP TRIGGER IF EXISTS highRater;
DELIMITER $$
CREATE TRIGGER highRater
AFTER INSERT ON Rating
FOR EACH ROW
BEGIN
	IF (new.stars=5)
	THEN UPDATE Customer Set discount=10 WHERE cID=new.cID;
	END IF;
END $$
DELIMITER ;


/*Update lastVisited when reservation is made*/
DROP TRIGGER IF EXISTS visiting;
CREATE TRIGGER visiting
AFTER INSERT ON Reservation
FOR EACH ROW 
UPDATE Customer Set updatedAt = now() WHERE cID=NEW.cID;


/* Procedure */
/* Archive customers */
DROP PROCEDURE IF EXISTS archiveCustomers;
DELIMITER $$
CREATE PROCEDURE archiveCustomers(IN oldDate DATE)
BEGIN
	START TRANSACTION;
    INSERT INTO arc_Customer(SELECT * FROM Customer WHERE updatedAt<oldDate);
    DELETE FROM Customer WHERE updatedAt<oldDate;
    COMMIT;
END $$
DELIMITER ;

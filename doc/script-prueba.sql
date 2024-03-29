DROP DATABASE bank;
CREATE DATABASE bank;
use bank;

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `LAST_NAME1` varchar(30) NOT NULL,
  `LAST_NAME2` varchar(30),
  `EMAIL` varchar(50) NOT NULL UNIQUE,
  `PHONE` varchar(20),
  `ACTIVE` Boolean NOT NULL DEFAULT 1,
  `PASSWORD` varchar(100) NOT NULL DEFAULT "newpassword",
  PRIMARY KEY (`ID`)
);

CREATE TABLE `CLIENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `LAST_NAME1` varchar(30) NOT NULL,
  `LAST_NAME2` varchar(30),
  `EMAIL` varchar(50) NOT NULL UNIQUE,
  `PHONE` varchar(20),
  `ACTIVE` Boolean NOT NULL DEFAULT 1,
  `IDENTIFIER` varchar(20) NOT NULL UNIQUE,
  `USER_ID` int(11),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`ID`)
);

CREATE TABLE `CURRENCY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL UNIQUE,
  `SYMBOL` varchar(10) NOT NULL,
  `CODE` varchar(15) NOT NULL UNIQUE,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `COUNTRY` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(2) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PREFIX` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `ENTITY` (
  `ID` int(2) NOT NULL AUTO_INCREMENT,
  `CODE` int(4) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `IBAN` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUNTRY_ID` int(2) NOT NULL,
  `COUNTRY_CONTROL` int(2) NOT NULL,
  `ENTITY_ID` int(2) NOT NULL,
  `BRANCH` int(4) NOT NULL,
  `ACCOUNT_CONTROL` int(2) NOT NULL,
  `ACCOUNT_NUMBER`int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY`(`ID`),
  FOREIGN KEY (`ENTITY_ID`) REFERENCES `ENTITY`(`ID`)
);

CREATE TABLE `ACCOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BALANCE` decimal(12,2) NOT NULL DEFAULT 0,
  `IBAN_ID` int(11),
  `CLIENT_ID` int(11),
  `CURRENCY_ID` int(11),
  `ACTIVE` Boolean NOT NULL DEFAULT 1,
  `IBAN` varchar(50) NOT NULL,
  `ROL` varchar(20) NOT NULL DEFAULT 'SECOND',
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`CLIENT_ID`) REFERENCES `CLIENT`(`ID`),
  FOREIGN KEY (`CURRENCY_ID`) REFERENCES `CURRENCY`(`ID`),
  FOREIGN KEY (`IBAN_ID`) REFERENCES `IBAN`(`ID`)
);

CREATE TABLE `FUND` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL UNIQUE,
  `REF_NUMBER` varchar(30) NOT NULL UNIQUE,
  `CURRENCY_ID` int(11),
  `ACTIVE` Boolean NOT NULL DEFAULT 1,
  `ACTIVE_DATE` DATE NOT NULL,
  `INACTIVE_DATE` DATE,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`CURRENCY_ID`) REFERENCES `CURRENCY`(`ID`)
);

CREATE TABLE `PRODUCT` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(30) NOT NULL,
    `ACTIVE` Boolean NOT NULL DEFAULT 1,
    `ACTIVE_DATE` DATE NOT NULL,
    `INACTIVE_DATE` DATE,
    PRIMARY KEY (`ID`)
);


CREATE TABLE `FUNDS_PRODUCTS` (
    `FUND_ID` int(11),
    `PRODUCT_ID` int(11),
    PRIMARY KEY (`FUND_ID`, `PRODUCT_ID`),
    FOREIGN KEY (`FUND_ID`) REFERENCES `FUND`(`ID`),
    FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT`(`ID`)
);

CREATE TABLE `TRANSACTION` (
    `ID` int(11) NOT NULL AUTO_INCREMENT,
    `DATE` datetime NOT NULL,
    `TRANSACTION_NUMBER` varchar(40) NOT NULL UNIQUE,
    `AMOUNT` decimal(12,2) NOT NULL,
    `ACCOUNT_ID` int(11),
    `FUND_ID` int(11),
    `CONCEPT` varchar(80),
    PRIMARY KEY (`ID`),
    FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `ACCOUNT`(`ID`),
    FOREIGN KEY (`FUND_ID`) REFERENCES `FUND`(`ID`)
);

INSERT INTO USER (NAME, LAST_NAME1, LAST_NAME2, EMAIL, PHONE) VALUES ("Emilio", "Perez", "Castellanos", "emilio@gmail.com", "67859474");
INSERT INTO CLIENT (NAME, LAST_NAME1, LAST_NAME2, EMAIL, PHONE, IDENTIFIER, USER_ID) VALUES ("Luis", "Monzon", null, "luis@gmail.com", "675485784", "47876543G", 1);
INSERT INTO CURRENCY (NAME, SYMBOL, CODE) VALUES ("Euro", "€", "EUR");
INSERT INTO CURRENCY (NAME, SYMBOL, CODE) VALUES ("Dolar", "$", "USD");
INSERT INTO CURRENCY (NAME, SYMBOL, CODE) VALUES ("Libra", "£", "GBP");
INSERT INTO CURRENCY (NAME, SYMBOL, CODE) VALUES ("Yen", "¥", "JPY");
INSERT INTO CURRENCY (NAME, SYMBOL, CODE) VALUES ("Franco Suizo", "CHF", "CHF");
INSERT INTO COUNTRY (CODE, NAME) VALUES ("FR", "Francia");
INSERT INTO COUNTRY (CODE, NAME) VALUES ("ES", "España");
INSERT INTO COUNTRY (CODE, NAME) VALUES ("US", "Estados Unidos");
INSERT INTO COUNTRY (CODE, NAME) VALUES ("GB", "Reino Unido");
INSERT INTO COUNTRY (CODE, NAME) VALUES ("JP", "Japon");
INSERT INTO ENTITY (CODE, NAME) VALUES (1234, "Banco Santander");
INSERT INTO ENTITY (CODE, NAME) VALUES (2345, "Banco Sabadell");
INSERT INTO ENTITY (CODE, NAME) VALUES (3456, "Banco Popular");
INSERT INTO IBAN (COUNTRY_ID, COUNTRY_CONTROL, ENTITY_ID, BRANCH, ACCOUNT_CONTROL, ACCOUNT_NUMBER) VALUES (1, 12, 1, 1234, 34, 1234567890);
INSERT INTO ACCOUNT (IBAN_ID, CLIENT_ID, CURRENCY_ID) VALUES (1, 1, 1);
INSERT INTO FUND (NAME, REF_NUMBER, CURRENCY_ID, ACTIVE_DATE) VALUES ("Sp500", "REF0034", 1, CURRENT_DATE());
INSERT INTO PRODUCT (NAME, ACTIVE_DATE) VALUES ("Hipoteca", CURRENT_DATE());
INSERT INTO TRANSACTION (DATE, TRANSACTION_NUMBER, AMOUNT, ACCOUNT_ID, FUND_ID, CONCEPT) VALUES (NOW(), "NUM0001", 50839028.89, 1, 1, "esto funciona");
INSERT INTO FUNDS_PRODUCTS (FUND_ID, PRODUCT_ID) VALUES (1, 1);

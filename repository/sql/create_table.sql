CREATE DATABASE restSpring;

USE restSpring;

CREATE TABLE PERSON (
   id int NOT NULL AUTO_INCREMENT,
   firstName varchar(35) NOT NULL,
   lastName varchar(35) NOT NULL,
   email varchar(80) NOT NULL UNIQUE,
   primary key(id)
);
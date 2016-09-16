CREATE DATABASE ANGULAR;

USE ANGULAR;

CREATE TABLE PERSON (
   id int NOT NULL AUTO_INCREMENT,
   firstName varchar(35) NOT NULL,
   lastName varchar(35) NOT NULL,
   email varchar(80) NOT NULL UNIQUE,
   primary key(id)
);

insert into Person (id, firstName, lastName, email) values (1, 'Mr', 'MMR', 'mmr@gmail.com');
insert into Person (id, firstName, lastName, email) values (2, 'As', 'MMR', 'mas@gmail.com');
insert into Person (id, firstName, lastName, email) values (3, 'Ry', 'MMR', 'mry@gmail.com');
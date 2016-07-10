drop TABLE students;

CREATE TABLE students (
     id INT NOT NULL AUTO_INCREMENT,
     firstName CHAR(30) NOT NULL,
     lastName CHAR(60) NOT NULL,
     gender CHAR(1) NOT NULL,
     phoneNumber CHAR(30) NOT NULL,
     dateOfBirth DATE NOT NULL,
     momName CHAR(60),
     dadName CHAR(60),
     SSN CHAR(15) NOT NULL,
     PRIMARY KEY (SSN)
);

ALTER TABLE students AUTO_INCREMENT=7300840;

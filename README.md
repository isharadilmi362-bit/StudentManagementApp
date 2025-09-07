# StudentManagementApp
#Database Setup

#Run the following in MySQL:

CREATE DATABASE studentdb;
USE studentdb;
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    course VARCHAR(100)
);

#How to Run

Update database username and password in the code if needed.

Compile the program:

javac -cp .;mysql-connector-j.jar SmallStudentApp.java


#Run the program:

java -cp .;mysql-connector-j.jar SmallStudentApp



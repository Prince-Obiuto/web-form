CREATE DATABASE IF NOT EXISTS jdbc;

USE jdbc;

CREATE TABLE IF NOT EXISTS attendees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    middle_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    dob DATE,
    reg_number VARCHAR(20),
    faculty VARCHAR(50),
    department VARCHAR(100),
    nationality VARCHAR(50),
    state VARCHAR(50),
    country VARCHAR(50)
);
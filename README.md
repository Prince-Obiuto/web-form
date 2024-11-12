# Web Form - README

## Overview

This project is a **Java-based web application** for collecting user information through an HTML form. The form captures details like personal information, academic background, and other relevant data, which are then stored in a MySQL database. The backend is implemented using **NanoHTTPD**, a lightweight web server for handling HTTP requests and responses. Also uses the mail handler from javax to handle mail requests.

The project is hosted on **Vercel** for production deployment, allowing users to submit information remotely. 

## Features
- User-friendly HTML form for information submission.
- Backend implemented in **Java** using NanoHTTPD.
- **MySQL** database integration for storing submitted data.
- Sends a confirmation email to the user.
- Redirects users to a confirmation page after successful data submission.

## Technologies
- **Java** for backend logic.
- **NanoHTTPD** as the lightweight HTTP server.
- **MySQL** for database storage.
- **Vercel** for hosting the web application.
- **Javax mail** for handling the mail server and sending a confirmation email.

## Getting Started

### Prerequisites
- **Java** (JDK 11 or above)
- **MySQL** database
- **Vercel** account for deployment

### Local Development Setup
1. **Clone the repository:**
   ```bash
   git clone <https://github.com/Prince-Obiuto/web-form/>
   ```
2. **Database Setup:**
   Create a MySQL database and a table named `attendees`:
   ```sql
   CREATE TABLE attendees (
       id INT AUTO_INCREMENT PRIMARY KEY,
       first_name VARCHAR(255),
       middle_name VARCHAR(255),
       last_name VARCHAR(255),
       email VARCHAR(255),
       dob DATE,
       reg_number VARCHAR(255),
       faculty VARCHAR(255),
       department VARCHAR(255),
       nationality VARCHAR(255),
       country VARCHAR(255),
       state_of_origin VARCHAR(255)
   );
   ```
3. **Configure Database:**
   Modify the `connect()` method in the `submit` class with your database credentials:
   ```java
   connection = DriverManager.getConnection("jdbc:mysql://<host>:<port>/<database>", "<username>", "<password>");
   ```

4. **Run the Project Locally:**
   Compile and run the `submit` class:
   ```bash
   javac submit.java
   java submit
   ```
   The server will start at `http://localhost:8080`.

### Vercel Deployment

1. **Create a Vercel Project:**
   Push the project to GitHub and connect the repository to Vercel.
   
2. **Configure Environment Variables:**
   In Vercel, add the following environment variables for your MySQL database:
   - `DB_HOST`: Your MySQL database host.
   - `DB_USER`: Your MySQL username.
   - `DB_PASS`: Your MySQL password.
   - `DB_NAME`: The name of the database.

3. **Deploy the Project:**
   Vercel will automatically detect the project and deploy it.

## How It Works

### 1. HTML Form
   The user submits the form with their information, which is sent to the `/submit` route of the NanoHTTPD server.

### 2. Backend (NanoHTTPD Server)
   The server receives the form data and inserts it into the MySQL database via the `insertAttendee()` method.

### 3. Database (MySQL)
   The MySQL database stores all the submitted data in the `attendees` table.

### 4. Confirmation Page
   After the form is submitted successfully, users are redirected to a confirmation page.

## Project Structure

```bash
.
├── index.html            # HTML form for user input
├── submit.java           # Java backend (NanoHTTPD)
├── confirmation.html     # Confirmation page displayed after submission
└── README.md             # Project README
```

## Troubleshooting

### MySQL Connection Issues
   - Verify your MySQL connection string.
   - Ensure the database is running and the credentials are correct.

### Data Not Saving
   - Double-check the parameter names in the form match the backend fields.
   - Ensure the MySQL table is correctly structured to receive data.

## Future Improvements
- Add form validation for better data quality.
- Implement user authentication for secure access to data.
- Add email notifications upon successful submission.

## License
This project is open-source. Feel free to modify it as per your needs.

//package com.princeobiuto.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@WebServlet("/submitForm")
public class submitForm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String firstName = request.getParameter("first_name");
        String middleName = request.getParameter("middle_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String regNumber = request.getParameter("reg_number");
        String faculty = request.getParameter("faculty");
        String department = request.getParameter("department");
        String nationality = request.getParameter("nationality");
        String state = request.getParameter("state");
        String country = request.getParameter("country");

        // Save data to MySQL
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "princeobiuto");

            String query = "INSERT INTO attendees (first_name, middle_name, last_name, email, dob, reg_number, faculty, department, nationality, state, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, firstName);
            pstmt.setString(2, middleName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, dob);
            pstmt.setString(6, regNumber);
            pstmt.setString(7, faculty);
            pstmt.setString(8, department);
            pstmt.setString(9, nationality);
            pstmt.setString(10, state);
            pstmt.setString(11, country);

            pstmt.executeUpdate();
            conn.close();

            // Send email after saving
            sendEmail(email, firstName, lastName, regNumber);

            // Send response to the client
            response.getWriter().println("Information saved successfully! A confirmation email has been sent.");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Registration failed!");
        }
    }

    private void sendEmail(String toEmail, String firstName, String lastName, String regNumber) {
        final String fromEmail = "princeamam9.com"; // Your email
        final String password = "Gudboi12345";  // Your email password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Information Gathering Confirmation");

            // Email content
            String content = "Dear " + firstName + " " + lastName + ",\n\n"
                    + "Thank you for providing us with your details for this experiment. Here are your details:\n"
                    +"Name:" + firstName + " " + lastName +",\n"
                    +"Date of Birth:" + dob + "\n"
                    + "Registration Number: " + regNumber + "\n"
                    +"Faculty:" + faculty + "\n"
                    +"Department:" + department + "\n\n"
                    + "Best regards,\nDev Team";

            message.setText(content);
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

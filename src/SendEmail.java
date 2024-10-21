import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;

public class SendEmail {

	private static Properties loadProperties() throws IOException {
        Properties property = new Properties();
        FileInputStream fileInput = new FileInputStream("config.properties");
        property.load(fileInput);
        return property;
	}

    public static void createEmail(String recipientEmail, String firstName, String lastName) {
    	Properties props = null;
		try {
			props = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
        final String username = props.getProperty("email.username");
        final String password = props.getProperty("email.password");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Registration Confirmation");
            message.setText(String.format("Dear %s %s,\n\nThank you for registering!", firstName, lastName));

            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
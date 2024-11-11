import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;

public class SendEmail {

	/*private static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        FileInputStream fileInput = new FileInputStream("config.properties");
        props.load(fileInput);
        return props;
	}*/

    public static void createEmail(String recipientEmail, String firstName, String lastName) {
    	Properties props = new Properties();
        //= null;
		/*try {
			props = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        final String username = princeamam9@gmail.com;
                //props.getProperty("email.username");
        final String password = osmz rafm oeab pbms;
                //props.getProperty("email.password");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

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
            String messageContent = "<h1>Dear Sir/Madam</h1> <p>Thank you for registering! Your registration was successful, and we look forward to seeing you at the event.\n\nBest regards,\nEvent Team</p>";
            message.setContent(messageContent, "text/html");

            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String confirmationMessage() {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Registration Confirmation</title>" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css\" rel=\"stylesheet\">" +
                "</head>" +
                "<body class=\"bg-yellow-500 flex items-center justify-center min-h-screen\">" +
                "    <div class=\"bg-black p-8 rounded-lg shadow-lg w-full max-w-md text-center\">" +
                "        <h2 class=\"text-2xl font-semibold text-yellow-600 mb-4\">Thank You for Registering!</h2>" +
                "        <p class=\"text-white mb-6\">We have received your registration details. You will receive an email confirmation shortly.</p>" +
                "        <a href=\"/\" class=\"inline-block bg-yellow-600 text-white py-2 px-4 rounded hover:bg-yellow-700 focus:outline-none focus:ring-2 focus:ring-yellow-500\">" +
                "            Back to Home" +
                "        </a>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}
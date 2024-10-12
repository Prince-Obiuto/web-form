import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class submit {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private static final String JDBC_USER = "root"; // MySQL username
    private static final String JDBC_PASSWORD = "princeobiuto"; // MySQL password

    // Method to establish a connection to the database
    private static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc\", \"root\", \"princeobiuto");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    // Method to insert a new attendee
    public static void insertAttendee(String firstName, String lastName, String email,
                                      String dateOfBirth, String regNumber, String department,
                                      String nationality, String stateOfOrigin) {
        String sql = "INSERT INTO attendees (first_name, last_name, email, date_of_birth, reg_number, department, nationality, state_of_origin) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, dateOfBirth);
            stmt.setString(5, regNumber);
            stmt.setString(6, department);
            stmt.setString(7, nationality);
            stmt.setString(8, stateOfOrigin);

            stmt.executeUpdate();
            System.out.println("Attendee inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test data for inserting an attendee
        insertAttendee("John", "Doe", "johndoe@gmail.com", "1995-05-10", "123456", "Computer Science", "Nigerian", "Lagos");
    }
}

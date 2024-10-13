import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class submit {

    // Method to establish a connection to the database
    private static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "princeobiuto");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Method to insert a new attendee
    public static void insertAttendee(String firstName, String middleName, String lastName, String email, String dob,
                                      String regNumber, String faculty, String department, String nationality,
                                      String country, String stateOfOrigin) {
        String sql = "INSERT INTO attendees (first_name, middle_name, last_name, email, dob, reg_number, faculty, department, nationality, country, state_of_origin) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (conn != null) {
                stmt.setString(1, firstName);
                stmt.setString(2, middleName);
                stmt.setString(3, lastName);
                stmt.setString(4, email);
                stmt.setString(5, dob);  // dob should be in YYYY-MM-DD format
                stmt.setString(6, regNumber);
                stmt.setString(7, faculty);
                stmt.setString(8, department);
                stmt.setString(9, nationality);
                stmt.setString(10, country);
                stmt.setString(11, stateOfOrigin);

                stmt.executeUpdate();
                System.out.println("Attendee inserted successfully.");
            } else {
                System.out.println("Failed to make connection to database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test data for inserting an attendee
        insertAttendee("John", "Michael", "Doe", "johndoe@gmail.com", "2001-05-10", "123456", "Science", "Computer Science", "Nigerian", "Nigeria", "Lagos");
    }
}

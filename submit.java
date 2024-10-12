import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class submit {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306";
    private static final String JDBC_USER = "root"; // MySQL username
    private static final String JDBC_PASSWORD = "princeobiuto"; // MySQL password

    // Method to establish a connection to the database
    private static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    // Method to execute SQL from a file
    public static void executeSqlFile(String filePath) {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sql;
            StringBuilder queryBuilder = new StringBuilder();
            while ((sql = br.readLine()) != null) {
                queryBuilder.append(sql).append("\n");
            }

            // Split the queries by semicolon (assuming each query ends with ';')
            String[] queries = queryBuilder.toString().split(";");

            // Execute each query
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    stmt.execute(query.trim());
                    System.out.println("Executed: " + query);
                }
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Path to the SQL file
        String sqlFilePath = "mysql.sql";

        // Execute the SQL file
        executeSqlFile(sqlFilePath);
    }
}
package quiz_application_fp_1303;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test_connector {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/1303_quiz_application";
        String username = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
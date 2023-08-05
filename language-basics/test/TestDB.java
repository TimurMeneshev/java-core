package test;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class TestDB {
    public static void main(String[] args) {
        try {
            runTest();
        } catch (SQLException e) {
            for (Throwable t : e) t.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runTest() throws SQLException, IOException {
        try (Connection conn = getConnection();
            Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE Greetings (Message CHAR (20))");
            stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");

            try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings")) {
                if (result.next()) System.out.println(result.getString(1));
                }
            stat.executeUpdate("DROP TABLE Greetings");
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        var props = new Properties();
        try (Reader in = Files.newBufferedReader(Path.of("test/database.properties"), StandardCharsets.UTF_8)) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}

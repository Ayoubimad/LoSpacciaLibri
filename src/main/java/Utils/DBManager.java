package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

public class DBManager {

    public static final String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/lospaccialibri?user=Ayoub&password" +
            "=Password&serverTimezone=" + TimeZone.getDefault().getID();

    public static String JDBC_Driver = null;
    public static String JDBC_URL = null;
    static Connection connection;

    public static void setConnection() {
        JDBC_Driver = JDBC_Driver_MySQL;
        JDBC_URL = JDBC_URL_MySQL;
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            if (JDBC_Driver == null || JDBC_URL == null) {
                throw new IllegalStateException("Illegal request. Call setConnection() before.");
            }
            try {
                Class.forName(JDBC_Driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(JDBC_URL);
            showMetadata();
        }
        return connection;
    }

    public static void showMetadata() throws SQLException {
        if (connection == null) {
            throw new IllegalStateException("Illegal request. Connection not established");
        }

    }

    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}

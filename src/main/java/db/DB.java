package db;

import helpers.PropertiesHelper;

import java.sql.*;

public class DB {
    private static Connection connection = null;
    private static ResultSet resultSet = null;

    public synchronized static void connect() {
        try {
            String dbName = PropertiesHelper.prop.getProperty("db.name");
            String jdbcURL = "jdbc:h2:" + System.getProperty("user.dir") + "\\src\\main\\resources\\db/" + dbName;
            String username = PropertiesHelper.prop.getProperty("db.username");
            String password = PropertiesHelper.prop.getProperty("db.password");
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public synchronized static ResultSet executeQuery(String sql) {
        try {
            connect();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return resultSet;
    }

    public synchronized static int executeUpdate(String sql) {
        connect();
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return 0;
    }

    public synchronized static void closeConnection() {
        try {
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}

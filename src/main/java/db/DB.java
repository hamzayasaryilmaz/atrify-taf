package db;

import helpers.PropertiesHelper;

import java.sql.*;

public class DB {
    //
    private static String s = System.getProperty("file.separator");
    private static Connection connection = null;
    private static ResultSet resultSet = null;

    public synchronized static void connect() {
        try {
            String jdbcURL = "";
            String jdbcPath = s + "src" + s + "main" + s + "resources" + s + "db";
            String dbName = PropertiesHelper.prop.getProperty("db.name");
            if (PropertiesHelper.prop.getProperty("db.url").isEmpty())
                jdbcURL = "jdbc:h2:" + System.getProperty("user.dir") + jdbcPath + "/" + dbName + ";AUTO_SERVER=TRUE";
            else
                jdbcURL = PropertiesHelper.prop.getProperty("db.url");
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

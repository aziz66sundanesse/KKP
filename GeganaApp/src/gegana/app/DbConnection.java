package gegana.app;

import chumbucket.log.LogInfo;
import org.apache.log4j.Level;

public class DbConnection {

    private static DbConnection instance = new DbConnection();
    private static String host;
    private static String user;
    private static String password;
    private static String dbName;

    private DbConnection() {
        try {
            setData();
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR : " + e);
        }
    }

    private java.sql.Connection createConnection() {
        java.sql.Connection connection = null;
        try {
            String url = "jdbc:mysql://" + host + "/" + dbName;
            connection = java.sql.DriverManager.getConnection(url, user, password);
        } catch (java.sql.SQLException e) {
            System.err.println("ERROR Create Connection: " + e);
        }
        return connection;
    }

    public static java.sql.Connection getConnection() {
        return instance.createConnection();
    }

    private void setData() {
        java.util.Properties properties = new java.util.Properties();
        java.io.FileInputStream fileProperties;
        try {
            fileProperties = new java.io.FileInputStream("setting.properties");
            properties.load(fileProperties);
            password = properties.getProperty("DB_PASSWORD");
            user = properties.getProperty("DB_USER");
            host = properties.getProperty("DB_HOST");
            dbName = properties.getProperty("DB_NAME");
        } catch (java.io.IOException er) {
            LogInfo.create(Level.ERROR, er.getMessage(), er, DbConnection.class);
        }
    }
}
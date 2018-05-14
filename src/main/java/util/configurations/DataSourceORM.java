package util.configurations;

import lombok.extern.log4j.Log4j;
import org.javalite.activejdbc.Base;

import java.sql.Connection;

@Log4j
public class DataSourceORM {

    private static String connectionUrl;
    private static Connection connection;

    private static String dbDefaultUser;
    private static String dbDefaultPassword;
    private static String dbDefaultServer;
    private static String dbDefaultDatabase;

    public static Connection getConnection() {

        if (!Base.hasConnection()) {

            dbDefaultUser = TestConfiguration.dbDefaultUser[0];
            dbDefaultPassword = TestConfiguration.dbDefaultUser[1];
            dbDefaultServer = TestConfiguration.dbDefaultServer;
            dbDefaultDatabase = TestConfiguration.dbDefaultDatabase;

            if (!TestConfiguration.connectionString.isEmpty()) {
                connectionUrl = TestConfiguration.connectionString;
            } else {
                connectionUrl = "jdbc:log4jdbc:oracle:thin:@" + dbDefaultServer + ":" + dbDefaultDatabase;
            }

            System.setProperty("activejdbc.log", "");

            System.out.println("Attempting connection to: " + connectionUrl);
            try {
                Base.open("net.sf.log4jdbc.DriverSpy", connectionUrl, dbDefaultUser, dbDefaultPassword);
            } catch (Exception ex) {
                log.error("Unable to create database connection", ex);
            }

            if (Base.hasConnection()) {
                connection = Base.connection();
                log.info("Database connection successful");
            } else {
                log.error("Database connection not created");
            }
        }

        return connection;
    }

    // close connection to DB
    public static void closeConnection() {
        if (Base.hasConnection())
            Base.close();
    }
}

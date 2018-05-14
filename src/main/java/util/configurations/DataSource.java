package util.configurations;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;

import java.sql.*;

@Log4j
public class DataSource {

    private static String connectionUrl;
    private static Connection connection;

    private static String dbDefaultUser;
    private static String dbDefaultPassword;
    private static String dbDefaultServer;
    private static String dbDefaultDatabase;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {

                dbDefaultUser = TestConfiguration.dbDefaultUser[0];
                dbDefaultPassword = TestConfiguration.dbDefaultUser[1];
                dbDefaultServer = TestConfiguration.dbDefaultServer;
                dbDefaultDatabase = TestConfiguration.dbDefaultDatabase;

                if (!TestConfiguration.connectionString.isEmpty()) {
                    connectionUrl = TestConfiguration.connectionString;
                } else {
                    connectionUrl = "jdbc:log4jdbc:oracle:thin:@" + dbDefaultServer + ":" + dbDefaultDatabase;
                }

                try {

                    try {
                        Class.forName("net.sf.log4jdbc.DriverSpy");
                    } catch (ClassNotFoundException e) {
                        log.error("No JDBC Driver detected", e);
                    }

                    connection = DriverManager.getConnection(connectionUrl, dbDefaultUser, dbDefaultPassword);
                    if (connection != null)
                        log.info("Database connection successful");

                } catch (SQLException e) {
                    log.error("Database connection not created", e);
                }
            }
        } catch (SQLException e) {
            log.error("Unable to verify Connection state", e);
        }

        return connection;
    }

    // close connection to DB
    public static void closeConnection() {
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            log.error("Problem while close connection", e);
        }
    }

    // Only for connection testing
    public static void main(String[] args) {

        util.Logger.init();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT SURNAME FROM USERS WHERE ROWNUM = 1");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            log.error("Problem while executing query", e);
            Assert.fail(e.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
            }
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}

package util.queries;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import util.configurations.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j
public class UserQueries {

    public static int getUserID(final String userName) {
        int id = -1;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String query = "SELECT ID FROM USERS WHERE NAME = ?";

        try {
            preparedStatement = DataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("ID");
            }

        } catch (final SQLException e) {
            log.error("Problem while executing query", e);
            Assert.fail(e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (final SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return id;
    }
}
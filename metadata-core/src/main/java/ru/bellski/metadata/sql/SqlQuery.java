package ru.bellski.metadata.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by oem on 5/11/16.
 */
public class SqlQuery {
    private final Connection connection;

    public SqlQuery(Connection connection) {
        this.connection = connection;
    }

    public SqlQueryResult select(String query) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            return new SqlQueryResult(ps.executeQuery(), connection);
        } catch (SQLException e) {
            connection.close();
            throw e;
        }
    }
}

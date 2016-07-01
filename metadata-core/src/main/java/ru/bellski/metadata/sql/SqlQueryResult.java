package ru.bellski.metadata.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oem on 5/11/16.
 */
public class SqlQueryResult {
    private final ResultSet resultSet;
    private final Connection connection;

    public SqlQueryResult(ResultSet resultSet, Connection connection) {
        this.resultSet = resultSet;
        this.connection = connection;
    }

    public <TYPE> List<TYPE> fetch(BeanListMapper<TYPE> beanListMapper) throws SQLException {
        try {
            return beanListMapper.fetch(resultSet);
        } finally {
            connection.close();
        }
    }

    public <TYPE> TYPE fetchOne(BeanMapper<TYPE> beanMapper) throws SQLException {
        try {
            return beanMapper.fetch(resultSet);
        } finally {
            connection.close();
        }
    }
}

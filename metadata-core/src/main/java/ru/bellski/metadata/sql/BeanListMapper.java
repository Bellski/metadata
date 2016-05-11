package ru.bellski.metadata.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oem on 5/11/16.
 */
@FunctionalInterface
public interface BeanListMapper<TYPE> {
	List<TYPE> fetch(ResultSet resultSet) throws SQLException;
}

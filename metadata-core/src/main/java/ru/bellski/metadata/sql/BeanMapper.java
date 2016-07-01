package ru.bellski.metadata.sql;

import java.sql.ResultSet;

/**
 * Created by oem on 5/11/16.
 */
@FunctionalInterface
public interface BeanMapper<TYPE> {
    TYPE fetch(ResultSet resultSet);
}

package ru.bellski.metadata.examples;

import ru.bellski.metadata.examples.domain.Company;
import ru.bellski.metadata.examples.domain.CompanySqlMetadata;
import ru.bellski.metadata.sql.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by oem on 5/11/16.
 */
public class CompanyTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.167:5432/cas2","cas2", "insaneinmybrain");

		String query =
			  "SELECT "
			+ "id AS \"" + CompanySqlMetadata.id + "\""
			+ ", title AS \"" + CompanySqlMetadata.name + "\""
			+ "FROM cas1.company";

		List<Company> result = SQL
			.connect(connection)
			.select(query)
			.fetch(CompanySqlMetadata::mapBeanList);

		System.out.println(result);
	}


}

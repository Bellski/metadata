package ru.bellski.metadata.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.select.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Created by oem on 5/11/16.
 */
public class SQL {
	private final DataSource ds;

	public SQL(DataSource ds) {
		this.ds = ds;
	}

	public SqlQuery connect() throws SQLException {
		return connect(ds.getConnection());
	}

	public static SqlQuery connect(Connection connection) {
		return new SqlQuery(connection);
	}


	public static void main(String[] args) throws JSQLParserException, IOException {




		Statement sql = CCJSqlParserUtil.parse("SELECT id, title AS name FROM cas1.company");
		sql.accept(new StatementVisitorAdapter() {
			@Override
			public void visit(Select select) {
				select.getSelectBody().accept(new SelectVisitor() {
					@Override
					public void visit(PlainSelect plainSelect) {
						plainSelect.getSelectItems().forEach(new Consumer<SelectItem>() {
							@Override
							public void accept(SelectItem selectItem) {
								System.out.println(selectItem);
							}
						});
					}

					@Override
					public void visit(SetOperationList setOperationList) {
//						System.out.println(setOperationList);
					}

					@Override
					public void visit(WithItem withItem) {
//						System.out.println(withItem);
					}
				});
			}
		});
	}
}

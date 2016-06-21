package ru.bellski.metasql.lang.generator.builder;

import org.jetbrains.annotations.Nullable;
import ru.bellski.metasql.lang.psi.MetaSqlReturnStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 6/16/16.
 */
public class ExecutorBuilder {
    private final String name;
    private final MetaSqlReturnStatement returnStatement;
    private String returnType;
    private String importList = "";

    public ExecutorBuilder(String name, @Nullable  MetaSqlReturnStatement returnStatement) {
        this.name = name;




        this.returnStatement = returnStatement;

        if (returnStatement != null) {
            this.returnType = returnStatement.getTypeReference().getLiteralType().getText();

        } else {
            returnType = "void";
        }
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getOverrideExecutions() {
        return "    @Override \n"
                + "    public " + returnType + " execute(Connection connection) throws SQLException { \n"
                + "        final PreparedStatement stmp = connection.prepareStatement(query);\n"
                + ExecuteStatementStrategy.buildTemplate(returnStatement)
                + "    } \n\n"
                + "    @Override \n"
                + "    public " + returnType + " execute() throws SQLException { \n"
                + "        try(Connection connection = ds.getConnection()) { \n"
                + "            " + (returnType.equals("void") ? "" : " return ") + "execute(connection); \n"
                + "        } \n"
                + "    }"
                ;
    }

    @Override
    public String toString() {
        return
                importList + "; \n"
                + "import java.sql.SQLException; \n"
                + "import java.sql.Connection;\n\n"
                + "public interface " + name + " { \n"
                + "    public " + returnType + " execute(Connection connection) throws SQLException;\n"
                + "    public " + returnType + " execute() throws SQLException;\n"
                + "}"
                ;
    }
}

package ru.bellski.metasql.lang.generator.builder;

import ru.bellski.metasql.lang.psi.MetaSqlReturnType;

/**
 * Created by oem on 6/16/16.
 */
public class ExecutorBuilder {
    private final String name;
    private final String returnType;

    public ExecutorBuilder(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getOverrideExecutions() {
        return "    @Override \n"
                + "    public " + returnType + " execute(Connection connection) { \n"
                + "    } \n\n"
                + "    @Override \n"
                + "    public " + returnType + " execute() { \n"
                + "    }"
                ;
    }

    @Override
    public String toString() {
        return
                "import java.sql.Connection;\n\n"
                + "public interface " + name + " { \n"
                + "    public " + returnType + " execute(Connection connection);\n"
                + "    public " + returnType + " execute();\n"
                + "}"
                ;
    }
}

package ru.bellski.metasql.lang.generator.builder;

import ru.bellski.metasql.lang.psi.MetaSqlLiteralType;
import ru.bellski.metasql.lang.psi.MetaSqlMetadata;
import ru.bellski.metasql.lang.psi.MetaSqlReturnStatement;

/**
 * Created by oem on 6/20/16.
 */
public class ExecuteStatementStrategy {
    public static String buildTemplate(MetaSqlReturnStatement returnStatement) {
        String template = "";
        //        if (returnStatement.getTypeWithGeneric() != null) {
        //            final MetaSqlLiteralType literalType = returnStatement.getTypeWithGeneric().getLiteralType();
        //
        //            if (literalType != null) {
        //                template =
        //                        "        final ResultSet resultSet = stmp.executeQuery();\n\n"
        //                                + "        final " + returnStatement.getReturnTypeName() + " result = new ArrayList<>();\n"
        //                                + "        while (resultSet.next()) {\n"
        //                                + "           result.add(resultSet.get"+ literalType.getTypeName()+ "(0));\n"
        //                                + "        }\n\n"
        //                                + "        return result;\n"
        //                ;
        //            } else if (returnStatement.getTypeWithGeneric().getMetadata() != null) {
        //                final MetaSqlMetadata metadata = returnStatement.getTypeWithGeneric().getMetadata();
        //
        //                template =
        //                        "        final ResultSet resultSet = stmp.executeQuery();\n\n"
        //                                + "        final " + returnStatement.getReturnTypeName() + " result = new ArrayList<>();\n"
        //                                + "        while (resultSet.next()) {\n"
        //
        //                                + "        }\n\n"
        //                                + "        return result;\n"
        //                ;
        //            }
        //
        //        } else if (returnStatement.getLiteralType() != null) {
        //            template =
        //                    "        final ResultSet resultSet = stmp.executeQuery();\n\n"
        //                    + "        "+ returnStatement.getReturnTypeName() + " result = null;\n"
        //                    + "        if (resultSet.next()) {\n"
        //                    + "           result = resultSet.get"+ returnStatement.getReturnTypeName()+ "(0);\n"
        //                    + "        }\n\n"
        //                    + "        return result;\n"
        //            ;
        //        }
        return template;
    }
}

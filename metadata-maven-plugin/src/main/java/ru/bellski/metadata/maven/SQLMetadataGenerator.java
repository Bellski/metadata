package ru.bellski.metadata.maven;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.SqlMetadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by oem on 5/6/16.
 */
public class SQLMetadataGenerator {

    public static JavaClassSource generate(Metadata<?> metadata) {
        final JavaClassSource sqlMetadataClass = Roaster.create(JavaClassSource.class).setPackage(metadata.getType().getPackage().getName() + ".meta").setName(metadata.getType().getSimpleName() + "SqlMetadata");

        sqlMetadataClass.addAnnotation(SqlMetadata.class);

        sqlMetadataClass.addImport(Map.class);

        StringBuilder body = new StringBuilder();
        appendNestedPropertyInstance(metadata.getType().getSimpleName(), metadata.getType().getSimpleName(), body);
        body.append("while (resultSet.next()) {");

        sqlMetadataClass.addField("public static Class<" + metadata.getType().getSimpleName() + "> type = " + metadata.getType().getSimpleName() + ".class" );

        generateSqlMetadataClass(null, metadata, new StringBuilder(), sqlMetadataClass, body).addMethod().setPublic().setStatic(true).setReturnType(metadata.getType()).setName("unmarshal").setBody(body.append("} \n").append("return ").append(down(metadata.getType().getSimpleName())).append(";").toString()).addThrows(SQLException.class).addParameter(ResultSet.class, "resultSet");
        return sqlMetadataClass;
    }

    private static JavaClassSource generateSqlMetadataClass(String propertyName, Metadata<?> metadata, StringBuilder prefix, JavaClassSource sqlMetadataClass, StringBuilder body) {
        final String typeName = metadata.getType().getSimpleName();

        sqlMetadataClass.addImport(metadata.getType());

        final StringBuilder name = new StringBuilder();

        for (MetaProperty metaProperty : metadata.getProperties()) {
            final String pName = metaProperty.getName();
            final String pTypeName = metaProperty.getType().getSimpleName();

            if (metaProperty.getType().getPackage() != null) {
                sqlMetadataClass.addImport(metaProperty.getType());
            }

            name.setLength(0);
            name.append(prefix).append(pName);
            if (metaProperty.isNested()) {
                appendNestedPropertyInstance(metaProperty.getMetadata().getType().getSimpleName(),pName,  body);

                if (propertyName == null) {
                    appendNestedPropertySetValue(typeName, up(pName), pName, body);
                } else {
                    appendNestedPropertySetValue(typeName, up(propertyName), pName, body);
                }

                generateSqlMetadataClass(pName, metaProperty.getMetadata(), name.append('.'), sqlMetadataClass, body);

            } else {
                final String selectParamName = name.toString().replaceAll("\\.", "_");

                appendSelectParamField(sqlMetadataClass, selectParamName, name.toString());

                if (propertyName == null) {
                    appendSetValue(typeName, pName, pTypeName, selectParamName, body);
                } else {
                    appendSetValue(propertyName, pName, pTypeName, selectParamName, body);
                }
            }
        }

        return sqlMetadataClass;
    }


    private static void appendNestedPropertyInstance(String type, String name,  StringBuilder body) {
        body.append("final ").append(type).append(" ").append(down(name)).append(" = ").append("new ").append(type).append("();\n");
    }

    private static void appendNestedPropertySetValue(String parentName, String parentPName, String pName, StringBuilder body) {
        body.append(down(parentName)).append(".set").append(parentPName).append("(").append(down(pName)).append(");\n");
    }

    private static void appendSelectParamField(JavaClassSource sqlMetadataClass, String selectParamName, String pathValue) {
        sqlMetadataClass.addField().setPublic().setStatic(true).setFinal(true).setType(String.class).setName(selectParamName).setStringInitializer(pathValue);
    }

    private static void appendSetValue(String typeName, String pName, String pTypeName, String selectParamName, StringBuilder body) {
        body.append(down(typeName)).append(".set").append(up(pName)).append("((").append(pTypeName).append(")").append("resultSet.getObject(").append(selectParamName).append("));").append("\n");
    }

    private static String up(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    private static String down(String value) {
        return Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }
}

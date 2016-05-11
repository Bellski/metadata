package ru.bellski.metadata.maven;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;

import java.util.Map;

/**
 * Created by oem on 5/6/16.
 */
public class SQLMetadataGenerator {

	public static JavaClassSource generate(Metadata<?> metadata) {
		final JavaClassSource sqlMetadataClass =
			Roaster
				.create(JavaClassSource.class)
				.setPackage(metadata.getType().getPackage().getName())
				.setName(metadata.getType().getSimpleName() + "SqlMetadata");

		sqlMetadataClass.addImport(Map.class);

		StringBuilder body = new StringBuilder();

		generateSqlMetadataClass(metadata, new StringBuilder(), sqlMetadataClass, body)
			.addMethod()
			.setPublic()
			.setStatic(true)
			.setName("unmarshal")
			.setParameters("Map<String, Object> data")
			.setBody(body.toString());

		return sqlMetadataClass;
	}

	private static JavaClassSource generateSqlMetadataClass(Metadata<?> metadata, StringBuilder prefix, JavaClassSource sqlMetadataClass, StringBuilder body){
		final String typeName = metadata.getType().getSimpleName();

		sqlMetadataClass.addImport(metadata.getType());

		appendNestedPropertyInstance(metadata.getType().getSimpleName(), body);

		final StringBuilder name = new StringBuilder();

		for (MetaProperty metaProperty : metadata.getProperties()) {
			final String pName = metaProperty.getName();
			final String pTypeName = metaProperty.getType().getSimpleName();

			sqlMetadataClass.addImport(metaProperty.getType());

			name.setLength(0);
			name
				.append(prefix)
				.append(pName)
			;
			if(metaProperty.isNested()){
				generateSqlMetadataClass(metaProperty.getMetadata(), name.append('.'), sqlMetadataClass, body);
				appendNestedPropertySetValue(typeName, metaProperty.getMetadata().getType().getSimpleName(), body);
			} else {
				final String selectParamName = name.toString().replaceAll("\\.", "_");

				appendSelectParamField(sqlMetadataClass, selectParamName, name.toString());
				appendSetValue(typeName, pName, pTypeName, selectParamName, body);
			}
		}

		return sqlMetadataClass;
	}


	private static void appendNestedPropertyInstance(String pName, StringBuilder body) {
		body
			.append("final ")
			.append(pName)
			.append(" ")
			.append(down(pName))
			.append(" = ")
			.append("new ")
			.append(pName)
			.append("();\n");
	}

	private static void appendNestedPropertySetValue(String nestedPropertyParamName, String nestedPropertyTypeName, StringBuilder body) {
		body
			.append(down(nestedPropertyParamName))
			.append(".set")
			.append(nestedPropertyTypeName)
			.append("(")
			.append(down(nestedPropertyTypeName))
			.append(");\n");
	}

	private static void appendSelectParamField(JavaClassSource sqlMetadataClass, String selectParamName, String pathValue) {
		sqlMetadataClass
			.addField()
			.setPublic()
			.setStatic(true)
			.setFinal(true)
			.setType(String.class)
			.setName(selectParamName)
			.setStringInitializer(pathValue);
	}

	private static void appendSetValue(String typeName, String pName, String pTypeName, String selectParamName, StringBuilder body) {
		body
			.append(down(typeName))
			.append(".set")
			.append(up(pName))
			.append("((")
			.append(pTypeName)
			.append(")")
			.append("data.get(")
			.append(selectParamName)
			.append("));")
			.append("\n");
	}

	private static String up(String value) {
		return Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

	private static String down(String value) {
		return Character.toLowerCase(value.charAt(0)) + value.substring(1);
	}
}

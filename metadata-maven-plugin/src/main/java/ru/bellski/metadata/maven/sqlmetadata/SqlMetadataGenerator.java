package ru.bellski.metadata.maven.sqlmetadata;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.maven.StringUtil;

/**
 * Created by oem on 6/30/16.
 */
public class SqlMetadataGenerator {
	public static JavaClassSource generate(Metadata<?> metadata) {
		final SqlMetadata sqlMetadata = new SqlMetadata(metadata);
		sqlMetadata.setPackage(metadata.getType().getPackage().getName() + ".meta");

		final BodyBuilder unmarshalMethodBuilder = sqlMetadata.createUnmarshalMethod();

		buildSqlMetadata(sqlMetadata, metadata, new StringBuilder(), null, unmarshalMethodBuilder);

		unmarshalMethodBuilder.onBuild();

		return sqlMetadata.getSource();
	}



	private static void buildSqlMetadata(SqlMetadata sqlMetadata,
										 Metadata<?> metadata,
										 StringBuilder prefix,
										 String propertyVariableName,
										 BodyBuilder unmarshalBuilder)  {
		final StringBuilder name = new StringBuilder();

		for (MetaProperty metaProperty : metadata.getProperties()) {
			final String pName = metaProperty.getName();
			final String capitalizedPName = StringUtil.capitalize(pName);
			final String pTypeName = metaProperty.getType().getSimpleName();

			name.setLength(0);
			name.append(prefix).append(pName);

			if (metaProperty.isNested()) {
				final String nestedVariableName = name.toString().replaceAll("\\.", "_");

				unmarshalBuilder
					.buildNestedBeanInstance(metaProperty.getType(), nestedVariableName);

				if (propertyVariableName == null) {
					try {
						metadata.getType().getMethod("set"+capitalizedPName, metaProperty.getType());
						unmarshalBuilder.buildSetterMethod("result_101", capitalizedPName, nestedVariableName, null);
					} catch (NoSuchMethodException e) {

					}

				} else {
					try {
						metadata.getType().getMethod("set"+capitalizedPName, metaProperty.getType());
						unmarshalBuilder.buildSetterMethod(propertyVariableName, capitalizedPName, nestedVariableName, null);
					} catch (NoSuchMethodException e) {

					}
				}

				buildSqlMetadata(sqlMetadata, metaProperty.getMetadata(), name.append('.'), nestedVariableName, unmarshalBuilder);
			} else {
				final String propertyFiledPath = name.toString();
				final String propertyFieldPathName = propertyFiledPath.replaceAll("\\.", "_");

				sqlMetadata.addPropertyPath(new SqlMetadata.PropertyField(metaProperty, propertyFieldPathName, propertyFiledPath));

				if (propertyVariableName == null) {
					try {
						metadata.getType().getMethod("set"+capitalizedPName, metaProperty.getType());
						unmarshalBuilder.buildSetterMethod("result_101", capitalizedPName, propertyFieldPathName, metaProperty.getType());
					} catch (NoSuchMethodException e) {

					}

				} else {
					try {
						metadata.getType().getMethod("set"+capitalizedPName, metaProperty.getType());
						unmarshalBuilder.buildSetterMethod(propertyVariableName, capitalizedPName, propertyFieldPathName, metaProperty.getType());
					} catch (NoSuchMethodException e) {

					}
				}
			}
		}
	}
}

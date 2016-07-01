package ru.bellski.metadata.maven.sqlmetadata;

import com.google.common.primitives.Primitives;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.sql.convertors.PropertyConvertorProvider;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 6/30/16.
 */
public class SqlMetadata {

	public static class PropertyField {
		private MetaProperty<?,?> property;
		private String name;
		private String path;;

		public PropertyField(MetaProperty<?, ?> property, String name, String path) {
			this.property = property;
			this.name = name;
			this.path = path;
		}

		public MetaProperty<?, ?> getProperty() {
			return property;
		}

		public void setProperty(MetaProperty<?, ?> property) {
			this.property = property;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}

	private final JavaClassSource sqlMetadataClass = Roaster.create(JavaClassSource.class);
	private final Metadata<?> rootMetadata;
	private String aPackage;

	public SqlMetadata(Metadata<?> rootMetadata) {
		this.rootMetadata = rootMetadata;

		sqlMetadataClass.setName(rootMetadata.getType().getSimpleName() + "SqlMetadata");
		sqlMetadataClass.addAnnotation(ru.bellski.metadata.SqlMetadata.class);

		addNecessaryImports();
	}

	public void setPackage(String aPackage) {
		this.aPackage = aPackage;
		sqlMetadataClass.setPackage(aPackage);
	}

	private void addNecessaryImports() {
		sqlMetadataClass.addImport(Set.class);
		sqlMetadataClass.addImport(HashSet.class);
	}


	public void addPropertyPath(PropertyField propertyField) {
		sqlMetadataClass
			.addField()
			.setPublic()
			.setStatic(true)
			.setFinal(true)
			.setName(propertyField.getName())
			.setType(String.class)
			.setLiteralInitializer("\"" + propertyField.getPath() + "\"");
	}

	public BodyBuilder createUnmarshalMethod() {
		final MethodSource<JavaClassSource> unmarshalMethod = sqlMetadataClass
			.addMethod()
			.setPublic()
			.setStatic(true)
			.setName("unmarshal")
			.setReturnType(rootMetadata.getType());

		unmarshalMethod.addParameter(ResultSet.class, "resultSet");
		unmarshalMethod.addThrows(SQLException.class);

		final StringBuilder unmarshalBody = new StringBuilder();

		final String rootBeanTypeName = rootMetadata.getType().getSimpleName();
		final String rootBeanVariableName = rootBeanTypeName.toLowerCase();

		unmarshalBody.append("final Set<String> columns = new HashSet<>();\n" +
			"\t\t\n" +
			"\t\tfor (int i = 1 ; i < resultSet.getMetaData().getColumnCount() +1 ; i++) {\n" +
			"\t\t\tcolumns.add(resultSet.getMetaData().getColumnName(i));\n" +
			"\t\t}");

		/*
			example: User(Metadata type) user(type name) = null;
		 */
		unmarshalBody
			.append(rootBeanTypeName).append(" ")
			.append("result_101").append(" ")
			.append(" = null;").append("\n");

		/*
			example: if (resultSet.next()) {
			 			user = new User();

		 */
		unmarshalBody
			.append("if (resultSet.next()) { \n")
			.append("result_101").append(" ")
			.append(" = new ").append(rootBeanTypeName).append("(); \n");

		return new BodyBuilder() {
			@Override
			public void onBuild() {
				unmarshalBody
					.append("} \n")
					.append("return result_101; \n");

				unmarshalMethod.setBody(unmarshalBody.toString());
			}

			@Override
			public BodyBuilder append(String string) {
				unmarshalBody.append(string);
				return this;
			}

			@Override
			public BodyBuilder buildNestedBeanInstance(Class type, String name) {
				sqlMetadataClass.addImport(type);

				unmarshalBody
					.append(type.getSimpleName()).append(" ")
					.append(name).append(" ")
					.append(" = new " )
					.append(type.getSimpleName())
					.append("(); \n");
				return this;
			}

			@Override
			public BodyBuilder buildSetterMethod(String propertyVariableName, String propertyName, String propertyFieldPath, Class propertyType) {

				if (propertyType != null) {
					unmarshalBody
						.append("if (columns.contains(")
						.append(propertyFieldPath)
						.append(")) { \n");

					if (propertyType.isPrimitive()) {
						propertyType = Primitives.wrap(propertyType);
					}

					if (propertyType.getPackage() != null) {
						sqlMetadataClass.addImport(propertyType);
					}

				}

				unmarshalBody
					.append(propertyVariableName)
					.append(".")
					.append("set").append(propertyName)
					.append("(");

				if (propertyType == null) {
					unmarshalBody.append(propertyFieldPath);
				} else {

					final PropertyConvertorProvider pProvider = PropertyConvertorProvider.get;

					if (pProvider.contains(propertyType)) {
						sqlMetadataClass.addImport(PropertyConvertorProvider.class);

						unmarshalBody
							.append("PropertyConvertorProvider \n")
							.append(".get \n")
							.append(".getConvertor(").append(propertyType.getSimpleName()).append(".class) \n")
							.append(".convert(\n")
							.append("resultSet\n")
							.append(".\n")
							.append("getObject(")
							.append(propertyFieldPath)
							.append(")\n")
							.append(")");

					} else {
						unmarshalBody
							.append("(")
							.append(propertyType.getSimpleName())
							.append(") ")
							.append("resultSet.getObject(")
							.append(propertyFieldPath)
							.append(")");
					}

				}

				unmarshalBody
					.append("); \n");

				if (propertyType != null) {
					unmarshalBody
						.append("} \n");
				}

				return this;
			}
		};
	}



	public JavaClassSource getSource() {
		return sqlMetadataClass;
	}

	@Override
	public String toString() {
		return sqlMetadataClass.toString();
	}

}

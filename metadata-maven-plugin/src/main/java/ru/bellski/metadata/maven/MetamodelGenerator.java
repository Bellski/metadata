package ru.bellski.metadata.maven;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by oem on 4/26/16.
 */
public class MetamodelGenerator {
	private static final String PACKAGE_REG_EXP = "\\{package\\}";
	private static final String IMPORT_REG_EXP = "\\{import\\}";
	private static final String CLASS_NAME_REG_EXP = "\\{name\\}";
	private static final String POJO_TYPE_REG_EXP = "\\{pojoType\\}";
	private static final String DOWN_POJO_TYPE_REG_EXP = "\\{down\\(pojoType\\)\\}";
	private static final String DOWN_PROP_TYPE_REG_EXP = "\\{down\\(propType\\)\\}";
	private static final String PROPERTY_NAMES_REG_EXP = "\\{propertyNames\\}";
	private static final String PROPERTIES_REG_EXP = "\\{properties\\}";
	private static final String PROP_NAME_REG_EXP = "\\{propName\\}";
	private static final String PROP_TYPE_REG_EXP = "\\{propType\\}";
	private static final String PROP_UP_NAME_REG_EXP = "\\{camel\\(propName\\)\\}";
	private static final String NESTED_META_REG_EXP = "\\{nestedMeta\\}";


	public static void generate(Logger logger, String src, final Set<Class<?>> pojos) throws IOException {
		for (Class<?> pojo : pojos) {
			generateMeta(logger, src,pojo, pojos);
		}
	}

	private static void generateMeta(Logger logger, String src, Class<?> aClass, Set<Class<?>> pojos) throws IOException {
		final String pojoTypeName = aClass.getSimpleName();

		logger.log(Level.SEVERE, "Generating meta data for -> " + pojoTypeName);

		final StringBuilder importBuilder = new StringBuilder();
		final StringBuilder propertiesBuilder = new StringBuilder();
		final StringBuilder propertyNamesBuilder = new StringBuilder();

		int count = 1;
		for (Field field : aClass.getDeclaredFields()) {
			propertyNamesBuilder.append(field.getName());

			if (count < aClass.getDeclaredFields().length) {
				propertyNamesBuilder.append(", ");
			}

			importBuilder.append(buildImport(field));
			propertiesBuilder.append(buildProperties(aClass.getSimpleName(), field, pojos));

			count++;
		}


		byte[] metaClassTemplate = Templates.META_CLASS.template
			.replaceAll(PACKAGE_REG_EXP, aClass.getPackage().getName())
			.replaceAll(IMPORT_REG_EXP, importBuilder.toString())
			.replaceAll(CLASS_NAME_REG_EXP, pojoTypeName)
			.replaceAll(POJO_TYPE_REG_EXP, pojoTypeName)
			.replaceAll(DOWN_POJO_TYPE_REG_EXP, down(pojoTypeName))
			.replaceAll(PROPERTY_NAMES_REG_EXP, propertyNamesBuilder.toString())
			.replaceAll(PROPERTIES_REG_EXP, propertiesBuilder.toString())
			.getBytes();


		new File(src + "/" + aClass.getPackage().getName().replaceAll("\\.", "/")).mkdirs();

		Files.write(Paths.get(src + "/" + aClass.getName().replaceAll("\\.", "/") + "Metadata.java"), metaClassTemplate);

		logger.log(Level.SEVERE, pojoTypeName + "Meta" + " have been created");
	}

	private static String buildProperties(String pojoTypeName, Field field, Set<Class<?>> pojos) {
		String template;
		if (pojos.contains(field.getType())) {
			template = Templates.NESTED_META_PROPERTY.template;
		} else {
			template = Templates.META_PROPERTY.template;
		}

		return template
			.replaceAll(PROP_NAME_REG_EXP, field.getName())
			.replaceAll(PROP_TYPE_REG_EXP, field.getType().getSimpleName())
			.replaceAll(POJO_TYPE_REG_EXP, pojoTypeName)
			.replaceAll(PROP_UP_NAME_REG_EXP, up(field.getName()))
			.replaceAll(NESTED_META_REG_EXP, pojoTypeName)
			.replaceAll(DOWN_POJO_TYPE_REG_EXP, down(pojoTypeName))
			.replaceAll(DOWN_PROP_TYPE_REG_EXP, down(field.getName()))
			+ "\n\n";
	}

	private static String buildImport(Field field) {
		return "import " + field.getType().getName() + ";\n";
	}

	private static String up(String value) {
		return Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

	private static String down(String value) {
		return Character.toLowerCase(value.charAt(0)) + value.substring(1);
	}
}

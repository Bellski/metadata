package ru.bellski.metadata.maven;

import org.apache.maven.plugin.logging.Log;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

/**
 * Created by oem on 4/26/16.
 */
public class MetadataGenerator {
	private static final String PACKAGE_REG_EXP = "\\{package\\}";
	private static final String IMPORT_REG_EXP = "\\{import\\}";
	private static final String META_CLASS_NAME_REG_EXP = "\\{metaClassName\\}";
	private static final String METADATA_TYPE_REG_EXP = "\\{metadataType\\}";
	private static final String DOWN_METADATA_TYPE_REG_EXP = "\\{down\\(metadataType\\)\\}";
	private static final String DOWN_PROPERTY_TYPE_REG_EXP = "\\{down\\(propertyType\\)\\}";
	private static final String PROPERTY_NAMES_REG_EXP = "\\{propertyNames\\}";
	private static final String PROPERTIES_REG_EXP = "\\{properties\\}";
	private static final String PROPERTY_NAME_REG_EXP = "\\{propertyName\\}";
	private static final String PROPERTY_TYPE_REG_EXP = "\\{propertyType\\}";
	private static final String CAMEL_PROPERTY_NAME_REG_EXP = "\\{camel\\(propertyName\\)\\}";
	private static final String NESTED_META_REG_EXP = "\\{nestedMeta\\}";


	public static void generate(Log logger, File src, final Set<Class<?>> generateMetadataCandidates) throws IOException {
		for (Class<?> generateMetadata : generateMetadataCandidates) {
			generating(logger, src, generateMetadata, generateMetadataCandidates);
		}
	}

	private static void generating(Log logger, File src, Class<?> generateMetadata, Set<Class<?>> generateMetadataCandidates) throws IOException {
		final String generateMetadataTypeName = generateMetadata.getSimpleName();


		final StringBuilder importBuilder = new StringBuilder();
		final StringBuilder propertiesBuilder = new StringBuilder();
		final StringBuilder propertyNamesBuilder = new StringBuilder();

		int count = 1;
		for (Field generateMetadataField : generateMetadata.getDeclaredFields()) {
			propertyNamesBuilder.append(generateMetadataField.getName());

			if (count < generateMetadata.getDeclaredFields().length) {
				propertyNamesBuilder.append(", ");
			}

			importBuilder.append(buildImport(generateMetadataField));
			propertiesBuilder.append(buildProperties(generateMetadata.getSimpleName(), generateMetadataField, generateMetadataCandidates));

			count++;
		}


		String metaClassTemplate = Templates.META_CLASS.template
			.replaceAll(PACKAGE_REG_EXP, generateMetadata.getPackage().getName())
			.replaceAll(IMPORT_REG_EXP, importBuilder.toString())
			.replaceAll(META_CLASS_NAME_REG_EXP, generateMetadataTypeName)
			.replaceAll(METADATA_TYPE_REG_EXP, generateMetadataTypeName)
			.replaceAll(DOWN_METADATA_TYPE_REG_EXP, down(generateMetadataTypeName))
			.replaceAll(PROPERTY_NAMES_REG_EXP, propertyNamesBuilder.toString())
			.replaceAll(PROPERTIES_REG_EXP, propertiesBuilder.toString());

		metaClassTemplate = Roaster.format(metaClassTemplate);

		Files.createDirectories(Paths.get(src + "/" + generateMetadata.getPackage().getName().replaceAll("\\.", "/")));
		Files.write(Paths.get(src + "/" + generateMetadata.getName().replaceAll("\\.", "/") + "Metadata.java"), metaClassTemplate.getBytes());
	}

	private static String buildProperties(String pojoTypeName, Field field, Set<Class<?>> pojos) {
		String template;
		if (pojos.contains(field.getType())) {
			template = Templates.NESTED_META_PROPERTY.template;
		} else {
			template = Templates.META_PROPERTY.template;
		}

		return template
			.replaceAll(PROPERTY_NAME_REG_EXP, field.getName())
			.replaceAll(PROPERTY_TYPE_REG_EXP, field.getType().getSimpleName())
			.replaceAll(METADATA_TYPE_REG_EXP, pojoTypeName)
			.replaceAll(CAMEL_PROPERTY_NAME_REG_EXP, up(field.getName()))
			.replaceAll(NESTED_META_REG_EXP, pojoTypeName)
			.replaceAll(DOWN_METADATA_TYPE_REG_EXP, down(pojoTypeName))
			.replaceAll(DOWN_PROPERTY_TYPE_REG_EXP, down(field.getName()))
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

package ru.bellski.metadata.maven.anewone;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import ru.bellski.metadata.AbstractMetadata;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.maven.forgeneration.GeneratedMetadata;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by oem on 5/10/16.
 */
public class MetadataGenerator {

	public static MetadataGeneratorResult generate(Class<?> candidateClass, Set<Class<?>> candidates) {
		final MetadataGeneratorResult result = new MetadataGeneratorResult();
		final String metadataName = candidateClass.getSimpleName() + "Metadata";

		final JavaClassSource metadataClass = Roaster
			.create(JavaClassSource.class)
			.setPackage(candidateClass.getPackage().getName())
			.setName(metadataName);

		final GeneratedMetadata generatedMetadata = new GeneratedMetadata(candidateClass);

		result.setMetadataClass(metadataClass);

		metadataClass.addImport(MetaProperty.class);
		metadataClass.addImport(Metadata.class);
		metadataClass.addImport(candidateClass);

		instantiateMetadataClassField(metadataClass, candidateClass.getSimpleName());

		result.setMetadataProperties(implementsProperties(metadataClass, addProperties(candidateClass, candidates, metadataClass, generatedMetadata)));

		extendsAbstractMetadata(candidateClass, candidateClass.getSimpleName(), metadataClass);

		return result;
	}


	private static void instantiateMetadataClassField(JavaClassSource metadataClass, String typeName) {
		metadataClass
			.addField()
			.setPublic()
			.setStatic(true)
			.setFinal(true)
			.setType(metadataClass)
			.setName(down(typeName))
			.setLiteralInitializer("new " + metadataClass.getName() + "();");
	}

	private static List<FieldSource> addProperties(Class<?> candidateClass, Set<Class<?>> candidates, JavaClassSource metadataClass, GeneratedMetadata generatedMetadata) {
		final ArrayList<FieldSource> properties = new ArrayList<>();

		for (Method getter : collectGetters(candidateClass, new ArrayList<>())) {
			final String propertyName = down(getter.getName().substring(3, getter.getName().length()));

			metadataClass.addImport(getter.getReturnType());

			if (candidates.contains(getter.getReturnType())) {
				metadataClass.addImport(getter.getReturnType().getName() + "Metadata");
			}

			properties.add(
				metadataClass.addField(buildProperty(candidateClass.getSimpleName(), propertyName, getter.getReturnType().getSimpleName(), candidates.contains(getter.getReturnType())))
			);


		}

		return properties;
	}

	private static List<Method> collectGetters(Class<?> aClass, List<Method> getters) {
		for (Method method : aClass.getDeclaredMethods()) {
			if (method.getName().startsWith("get")) {
				getters.add(method);
			}
		}

		if (aClass.getSuperclass() != Object.class) {
			collectGetters(aClass.getSuperclass(), getters);
		}

		return getters;
	}

	private static String buildProperty(String typeName, String propertyName, String propertyType, boolean isNested) {
		return
			"private final MetaProperty<" + typeName + ", " + propertyType + "> " + propertyName + " = new MetaProperty<" + typeName + ", " + propertyType + ">() {"
				+ "@Override \n"
				+ "public String getName() { \n"
				+ "return \"" + propertyName + "\";\n"
				+ "} \n"

				+ "@Override \n"
				+ "public Class<" + propertyType + "> getType() { \n"
				+ "return " + propertyType + ".class;"
				+ " } \n"

				+ "@Override \n"
				+ "public void setValue(" + typeName + " " + down(typeName) + ", " + propertyType + " value) { \n"
				+ down(typeName) + ".set" + up(propertyName) + "(value);"
				+ "} \n"

				+ "@Override \n"
				+ "public " + propertyType + " getValue(" + typeName + " " + down(typeName) + ") { \n"
				+ " return " + down(typeName) + ".get" + up(propertyName) + "();"
				+ "} \n"

				+ "@Override \n"
				+ "public boolean isNested() { \n"
				+ " return " + isNested + ";"
				+ "} \n"

				+ "@Override \n"
				+ "public Metadata<" + propertyType + "> getMetadata() { \n"
				+ (isNested ? " return " + propertyType + "Metadata." + propertyName + ";" : " return null;")
				+ "} \n"

				+ "};"
			;
	}

	private static JavaInterfaceSource implementsProperties(JavaClassSource metadataClass, List<FieldSource> properties) {
		final JavaInterfaceSource propertiesInterface = Roaster
			.create(JavaInterfaceSource.class)
			.setPackage(metadataClass.getPackage())
			.setName(metadataClass.getName() + "Properties");

		propertiesInterface.addTypeVariable("PROPERTY");

		for (FieldSource property : properties) {
			propertiesInterface
				.addMethod()
				.setReturnType("PROPERTY")
				.setName(property.getName());
		}

		metadataClass.addInterface(propertiesInterface.getQualifiedName() + "<MetaProperty>");
		metadataClass.implementInterface(propertiesInterface);

		for (FieldSource property : properties) {
			metadataClass
				.getMethod(property.getName())
				.setPublic()
				.setReturnType(property.getType().getQualifiedNameWithGenerics())
				.setBody("return " + property.getName() + ";");
		}

		return propertiesInterface;
	}

	private static void extendsAbstractMetadata(Class<?> type, String typeName, JavaClassSource metadataClass) {
		metadataClass.extendSuperType(AbstractMetadata.class);
		metadataClass.setSuperType(AbstractMetadata.class.getName() + "<" + typeName + ">");

		metadataClass
			.getMethod("create")
			.setReturnType(type)
			.setBody("return new " + typeName + "();");

		metadataClass
			.getMethod("getType")
			.setReturnType("Class<" + type.getName() + ">")
			.setBody("return " + typeName + ".class;");
	}

	private static String up(String value) {
		return Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

	private static String down(String value) {
		return Character.toLowerCase(value.charAt(0)) + value.substring(1);
	}
}

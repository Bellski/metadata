package ru.bellski.metadata.maven;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.NestedMetaProperty;
import ru.bellski.metadata.impl.AbstractMetadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by oem on 4/28/16.
 */
public class MetadataGenerator2 {
    private final File src;
    private final Set<Class<?>> generateMetadataCandidates;

    public MetadataGenerator2(File src, Set<Class<?>> generateMetadataCandidates) {
        this.src = src;
        this.generateMetadataCandidates = generateMetadataCandidates;
    }

    public void generate() throws IOException {
        for (Class<?> aClass : generateMetadataCandidates) {
            Files.createDirectories(Paths.get(src + "/" + aClass.getPackage().getName().replaceAll("\\.", "/")));
            Files.write(Paths.get(src + "/" + aClass.getName().replaceAll("\\.", "/") + "Metadata.java"), generateMetaClass(Roaster.create(JavaClassSource.class), aClass).getBytes());
        }
    }

    private String generateMetaClass(JavaClassSource metadataClass, Class<?> metadataForType) {
        metadataClass.setPackage(metadataForType.getPackage().getName());

        metadataClass
                .addField()
                .setName(down(metadataForType.getSimpleName()))
                .setPublic()
                .setStatic(true)
                .setFinal(true)
                .setType(metadataForType.getName() + "Metadata")
                .setLiteralInitializer("new " + metadataForType.getSimpleName() + "Metadata();");


        final StringBuilder propertiesNamesBuilder = new StringBuilder();
        final List<Method> getters = collectGetters(metadataForType, new ArrayList<>());

        int count = 1;
        for (Method getter : getters) {
            final String propertyName = down(getter.getName().substring(3, getter.getName().length()));

            buildProperty(getter, propertyName, metadataClass, metadataForType);

            if (count < getters.size()) {
                propertiesNamesBuilder.append(propertyName);
                propertiesNamesBuilder.append(", ");
            } else {
                propertiesNamesBuilder.append(propertyName);
            }

            count++;
        }

        metadataClass
                .addMethod()
                .setPrivate()
                .setBody("addProperties(" + propertiesNamesBuilder + ");")
                .setConstructor(true);

        metadataClass
                .setName(metadataForType.getSimpleName() + "Metadata")
                .extendSuperType(AbstractMetadata.class)
                .setSuperType(AbstractMetadata.class.getSimpleName() + "<" + metadataForType.getSimpleName() + ">")
                .addImport(metadataForType.getName());

        metadataClass
                .getMethod("createTYPE")
                .setReturnType(metadataForType.getSimpleName())
                .setBody("return new " + metadataForType.getSimpleName() + "();");

        return metadataClass.toString();
    }

    private List<Method> collectGetters(Class<?> aClass, List<Method> getters) {
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

    private void buildProperty(Method getter, String propertyName, JavaClassSource metadataClass, Class<?> metadataForType) {
        final FieldSource<JavaClassSource> property = metadataClass.addField();

        metadataClass.addImport(getter.getReturnType());

        property
                .setName(propertyName)
                .setPublic()
                .setFinal(true);

        String template;

        final String generics = "<" + metadataForType.getSimpleName() + ", " + getter.getReturnType().getSimpleName() + ">";

        if (generateMetadataCandidates.contains(getter.getReturnType())) {
            template = Templates.NESTED_META_PROPERTY.template;

            metadataClass.addImport(Metadata.class);
            metadataClass.addImport(getter.getReturnType().getName() + "Metadata");

            property.setType(NestedMetaProperty.class.getName() + generics);
        } else {
            template = Templates.META_PROPERTY.template;

            property.setType(MetaProperty.class.getName() + generics);
        }

        template = template.replaceAll("\\{metadataType\\}", metadataForType.getSimpleName());
        template = template.replaceAll("\\{propertyType\\}", getter.getReturnType().getSimpleName());
        template = template.replaceAll("\\{propertyName\\}", propertyName);
        template = template.replaceAll("\\{camel\\(propertyName\\)\\}", getter.getName().substring(3, getter.getName().length()));
        template = template.replaceAll("\\{down\\(propertyType\\)\\}", down(getter.getReturnType().getSimpleName()));

        property.setLiteralInitializer(template);
    }

    private static String up(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    private static String down(String value) {
        return Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }
}

package ru.bellski.metadata.maven;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import ru.bellski.metadata.impl.AbstractMetadata;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by oem on 4/28/16.
 */
public class MetadataGenerator2 {
	public static void generate(File src, final Set<Class<?>> generateMetadataCandidates) throws FileNotFoundException {
		generateMetadataCandidates.forEach(new Consumer<Class<?>>() {
			@Override
			public void accept(Class<?> aClass) {
				System.out.println(generateMetaClass(Roaster.create(JavaClassSource.class), aClass, generateMetadataCandidates));
			}
		});
	}

	private static String generateMetaClass(JavaClassSource metadataClass, Class<?> metadataForType, Set<Class<?>> generateMetadataCandidates) {
		metadataClass
			.setName(metadataForType.getSimpleName() + "Metadata")
			.extendSuperType(AbstractMetadata.class)
			.setSuperType(AbstractMetadata.class.getSimpleName() + "<" + metadataForType.getSimpleName() + ">")
			.addImport(metadataForType.getName());

		for (Field field : metadataForType.getDeclaredFields()) {
			System.out.println(field.getName());
		}

		metadataClass
			.getMethod("createTYPE")
			.setReturnType(metadataForType.getSimpleName())
			.setBody("return new " + metadataForType.getSimpleName() + "();");

		return metadataClass.toString();
	}


}

package ru.bellski.metadata.maven;

import org.reflections.Reflections;
import ru.bellski.metadata.annotaion.GenerateMetadata;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * Created by oem on 4/27/16.
 */
public class GenerateMetadataFinder {
	public static Set<Class<?>> find(String inPackage, ClassLoader classLoader) {
		return new Reflections(inPackage, classLoader).getTypesAnnotatedWith(GenerateMetadata.class);
	}
}

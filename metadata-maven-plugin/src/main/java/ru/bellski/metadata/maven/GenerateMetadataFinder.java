package ru.bellski.metadata.maven;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by oem on 4/27/16.
 */
public class GenerateMetadataFinder {
	public static Set<Class<?>> find(String inPackage, ClassLoader classLoader) {
        ConfigurationBuilder conf = new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(inPackage, classLoader))
                .setScanners(new SubTypesScanner(false));

        conf.setClassLoaders(new ClassLoader[] {classLoader});
        conf.filterInputsBy(new FilterBuilder().includePackage(inPackage));

		return new Reflections(
                conf
		).getAllTypes().stream().map(s -> {
			try {
				return Class.forName(s, false, classLoader);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toSet());
	}
}

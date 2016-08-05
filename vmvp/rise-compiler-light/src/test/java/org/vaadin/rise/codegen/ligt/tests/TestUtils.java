package org.vaadin.rise.codegen.ligt.tests;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.JavaFileObjects;

import javax.tools.JavaFileObject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by oem on 8/5/16.
 */
public class TestUtils {
	public static List<JavaFileObject> loadSources(String from, String... sources) throws IOException, URISyntaxException {
		final Path path = Paths.get(TestUtils.class.getResource(from).toURI());

		final ImmutableList.Builder<JavaFileObject> jfoList = ImmutableList.builder();

		for (String source : sources) {
			jfoList.add(JavaFileObjects.forResource(path.resolve(source).toUri().toURL()));
		}

		return jfoList.build();
	}
}

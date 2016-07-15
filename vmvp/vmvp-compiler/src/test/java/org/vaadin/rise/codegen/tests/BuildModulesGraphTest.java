package org.vaadin.rise.codegen.tests;

import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;
import org.vaadin.rise.codegen.RiseProcessor;

import javax.tools.JavaFileObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * Created by oem on 7/15/16.
 */
public class BuildModulesGraphTest {


	@Test
	public void buildRiseModulesGraph() throws IOException {
		final List<JavaFileObject> generatedSource = loadSources("/javasource/generated/");

		final RiseProcessor processor = new RiseProcessor();
		final CompileTester.GeneratedPredicateClause<CompileTester.SuccessfulCompilationClause> and = assertAbout(javaSources()).that(loadSources("/javasource"))
			.processedWith(processor)
			.compilesWithoutError()
			.and();

		generatedSource.forEach(javaFileObject -> and.generatesSources(javaFileObject));

	}

	private static List<JavaFileObject> loadSources(String from) throws IOException {
		final Path path = Paths.get(BuildModulesGraphTest.class.getResource(from).getPath());

		return Files
			.walk(path, 1)
			.filter(path1 -> path1.toString().endsWith(".java"))
			.map(path1 -> {
				try {
					return JavaFileObjects.forResource(path1.toUri().toURL());
				} catch (MalformedURLException e) {
					throw new RuntimeException(e);
				}
			})
			.collect(Collectors.toList());
	}

}

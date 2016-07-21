package org.vaadin.rise.codegen.tests;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;
import dagger.internal.codegen.ComponentProcessor;
import org.junit.Ignore;
import org.junit.Test;
import org.vaadin.rise.codegen.RiseProcessor;
import org.vaadin.rise.codegen.helpers.Compilation;

import javax.tools.JavaFileObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * Created by oem on 7/15/16.
 */
public class BuildModulesGraphTest {


	@Test
	@Ignore
	public void buildRiseModulesGraph() throws IOException, URISyntaxException {
		final List<JavaFileObject> generatedSource = loadSources("/javasource/generated/");

		final RiseProcessor processor = new RiseProcessor();
		final CompileTester.GeneratedPredicateClause<CompileTester.SuccessfulCompilationClause> and = assertAbout(javaSources()).that(loadSources("/javasource"))
			.processedWith(processor)
			.compilesWithoutError()
			.and();

		generatedSource.forEach(javaFileObject -> and.generatesSources(javaFileObject));

	}

	@Test
	@Ignore
	public void testDaggerGeneration() throws IOException, URISyntaxException {
		final List<JavaFileObject> generatedSource = loadSources("/javasource/generated/");

		System.out.println(generatedSource);

		final ImmutableList<JavaFileObject> sources = Compilation
				.compile(Collections.singletonList(new ComponentProcessor()), Collections.emptyList(), generatedSource)
				.generatedSources();
	}

	private static List<JavaFileObject> loadSources(String from) throws IOException, URISyntaxException {
		final Path path = Paths.get(BuildModulesGraphTest.class.getResource(from).toURI());

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

	private static List<JavaFileObject> loadAllSources() throws IOException, URISyntaxException {
		final Path path = Paths.get(BuildModulesGraphTest.class.getResource("/javasource").toURI());

		return Files
				.walk(path)
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

	@Test
	@Ignore
	public void testGenerate() {
		JavaFileObject javaFileObject = JavaFileObjects.forSourceString(
				"ModuleS"
				,
				"import dagger.Module;\n" +
						"import dagger.Provides;\n" +
						"\n" +
						"import javax.inject.Singleton;\n" +
						"\n" +
						"/**\n" +
						" * Created by Aleksandr on 16.07.2016.\n" +
						" */\n" +
						"@Module\n" +
						"public class ModuleS {\n" +
						"\n" +
						"    @Provides @Singleton\n" +
						"    public String providesS() {\n" +
						"        return \"AAA\";\n" +
						"    }\n" +
						"}"
		);


		final Compilation.Result compile = Compilation.compile(Collections.singletonList(new ComponentProcessor()), Collections.emptyList(), Collections.singletonList(javaFileObject));

		String s = "";
	}

}

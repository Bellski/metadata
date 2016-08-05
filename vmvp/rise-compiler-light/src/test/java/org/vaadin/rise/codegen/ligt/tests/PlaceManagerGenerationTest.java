package org.vaadin.rise.codegen.ligt.tests;

import com.google.testing.compile.CompileTester;
import org.junit.Test;
import org.vaadin.rise.codegen.light.RiseLightProcessor;

import javax.tools.JavaFileObject;
import java.util.List;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static org.vaadin.rise.codegen.ligt.tests.TestUtils.loadSources;

/**
 * Created by oem on 8/5/16.
 */
public class PlaceManagerGenerationTest {
	@Test
	public void test() throws Exception {
		final List<JavaFileObject> sources = loadSources("/codegen/presenters", "HomePresenter.java", "UserPresenter.java");

		final RiseLightProcessor riseLightProcessor = new RiseLightProcessor();
		final CompileTester.GeneratedPredicateClause<CompileTester.SuccessfulCompilationClause> and = assertAbout(javaSources()).that(sources)
			.processedWith(riseLightProcessor)
			.compilesWithoutError()
			.and();

		loadSources("/codegen/generated/", "PlaceManagerModule.java").forEach(javaFileObject -> and.generatesSources(javaFileObject));
	}
}

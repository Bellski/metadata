package org.vaadin.rise.codegen.tests;


import com.google.testing.compile.JavaFileObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.vaadin.rise.codegen.RiseProcessor;

import javax.tools.JavaFileObject;
import java.util.Arrays;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * Created by oem on 7/12/16.
 */
@RunWith(JUnit4.class)
public class ApplicationEntryValidation {



	@Test
	public void doubleApplicationEntry() {
		JavaFileObject somePresenterFile = JavaFileObjects.forSourceLines(
			"SomePresenter",
			"package org.vaadin.rise.test.application;\n" +
				"\n" +
				"import org.vaadin.rise.core.RisePresenterImpl;\n" +
				"\n" +
				"/**\n" +
				" * Created by oem on 7/12/16.\n" +
				" */\n" +
				"public class SomePresenter extends RisePresenterImpl {\n" +
				"}"
		);


		JavaFileObject presenterFile = JavaFileObjects.forSourceLines(
			"ApplicationPresenter",
			"package org.vaadin.rise.test.application;\n" +
				"\n" +
				"import org.vaadin.rise.place.annotation.DefaultPlace;\n" +
				"\n" +
				"/**\n" +
				" * Created by oem on 7/12/16.\n" +
				" */\n" +
				"@DefaultPlace\n" +
				"public class ApplicationPresenter extends SomePresenter {\n" +
				"}\n"
		);

		JavaFileObject testApplicationEntryFile = JavaFileObjects.forSourceLines(
			"TestApplicationEntry",
			"package org.vaadin.rise.test.application;\n" +
				"\n" +
				"import org.vaadin.rise.annotation.ApplicationEntry;\n" +
				"\n" +
				"/**\n" +
				" * Created by oem on 7/12/16.\n" +
				" */\n" +
				"@ApplicationEntry(getView = Object.class, getPresenter = ApplicationPresenter.class)\n" +
				"public class TestApplicationEntry {\n" +
				"}"
		);



		assertAbout(javaSources()).that(Arrays.asList(somePresenterFile, presenterFile, testApplicationEntryFile))
			.processedWith(new RiseProcessor())
			.compilesWithoutError();
	}


}

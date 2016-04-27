package ru.bellski.metadata.maven.test;

import com.google.common.collect.Sets;
import org.junit.Test;
import ru.bellski.metadata.maven.GenerateMetadataCompiler;
import ru.bellski.metadata.maven.MetadataGenerator;
import ru.bellski.metadata.maven.test.domain.Address;
import ru.bellski.metadata.maven.test.domain.User;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Created by oem on 4/26/16.
 */
public class MetadataGeneratorTests {
	private static final String PACKAGE = "ru.bellski.metadata.maven.test.domain";
	private static final Path JAVA_SOURCE = Paths.get("/home/oem/My/programming/projects/metadata/metadata-maven-plugin/src/test/java");
	private static final Path CLASSES_PATH = Paths.get("/home/oem/My/programming/projects/metadata/metadata-maven-plugin/target/classes");

	@Test
	public void metadataGenerationTest() throws Exception {
		MetadataGenerator.generate(
			Logger.getAnonymousLogger(),
			"/home/oem/My/programming/projects/metadata/metadata-maven-plugin/src/test/java",
			Sets.newHashSet(User.class, Address.class)
		);
	}

	@Test
	public void generateMetadataCompilerTest() throws Exception {
		GenerateMetadataCompiler.compile(JAVA_SOURCE, PACKAGE, CLASSES_PATH);
	}
}


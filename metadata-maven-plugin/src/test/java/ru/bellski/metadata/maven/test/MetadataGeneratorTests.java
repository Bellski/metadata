package ru.bellski.metadata.maven.test;

import com.google.common.collect.Sets;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.junit.Test;
import ru.bellski.metadata.maven.GenerateMetadataCompiler;
import ru.bellski.metadata.maven.anewone.MetadataGenerator;
import ru.bellski.metadata.maven.anewone.MetadataGeneratorResult;
import ru.bellski.metadata.maven.anewone.SQLMetadataGenerator;
import ru.bellski.metadata.maven.test.domain2.Address;
import ru.bellski.metadata.maven.test.domain2.PermanentAddress;
import ru.bellski.metadata.maven.test.domain2.User;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.function.Consumer;

/**
 * Created by oem on 4/26/16.
 */
public class MetadataGeneratorTests {
	private static final String PACKAGE = "ru.bellski.metadata.maven.test.domain";
	private static Path JAVA_SOURCE;
	private static final Path CLASSES_PATH = Paths.get("/home/oem/My/programming/projects/metadata/metadata-maven-plugin/target/classes");
	private static Path GENERATED_SOURCES;

	{


        try {

            Path root = Paths.get(MetadataGeneratorTests.class.getClassLoader().getResource(".").toURI());
            GENERATED_SOURCES = Paths.get(root.getParent() + "/generated-test-sources/address");

            JAVA_SOURCE = Paths.get(root.getParent().getParent() + "/src/test/java");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


	}


//	@Test
	public void generateMetadataCompilerTest() throws Exception {
		GenerateMetadataCompiler.compile(JAVA_SOURCE, PACKAGE, CLASSES_PATH, null);
	}


	@Test
	public void sqlMetageneratorTest() throws Exception {
		final HashSet<Class<?>> candidates = Sets.newHashSet(User.class, Address.class, PermanentAddress.class);

		for (Class<?> candidate : candidates) {
			MetadataGeneratorResult result = MetadataGenerator.generate(candidate, candidates);

			Files.write(Paths.get(JAVA_SOURCE + "/" + result.getMetadataClass().getQualifiedName().replaceAll("\\.", "/") + ".java"), result.getMetadataClass().toString().getBytes());
			Files.write(Paths.get(JAVA_SOURCE + "/" + result.getMetadataProperties().getQualifiedName().replaceAll("\\.", "/") + ".java"), result.getMetadataProperties().toString().getBytes());
		}

	}
}


package ru.bellski.metadata.maven.test;

import com.google.common.collect.Sets;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.junit.Test;
import ru.bellski.metadata.maven.GenerateMetadataCompiler;
import ru.bellski.metadata.maven.MetadataGenerator;
import ru.bellski.metadata.maven.MetadataGenerator2;
import ru.bellski.metadata.maven.test.domain.Address;
import ru.bellski.metadata.maven.test.domain.User;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

/**
 * Created by oem on 4/26/16.
 */
public class MetadataGeneratorTests {
	private static final String PACKAGE = "ru.bellski.metadata.maven.test.domain";
	private static final Path JAVA_SOURCE = Paths.get("/home/oem/My/programming/projects/metadata/metadata-maven-plugin/src/test/java");
	private static final Path CLASSES_PATH = Paths.get("/home/oem/My/programming/projects/metadata/metadata-maven-plugin/target/classes");

//	@Test
	public void metadataGenerationTest() throws Exception {
		MetadataGenerator.generate(
			null,
			new File("/home/oem/My/programming/projects/metadata/metadata-maven-plugin/src/test/java"),
			Sets.newHashSet(User.class, Address.class)
		);
	}

//	@Test
	public void generateMetadataCompilerTest() throws Exception {
		GenerateMetadataCompiler.compile(JAVA_SOURCE, PACKAGE, CLASSES_PATH, null);
	}

	@Test
	public void name() throws Exception {
		JavaClassSource user = Roaster.parse(JavaClassSource.class, new File("/home/oem/My/programming/projects/metadata/metadata-examples/src/main/java/ru/bellski/metadata/examples/User.java"));

		user.getFields().forEach(new Consumer<FieldSource<JavaClassSource>>() {
			@Override
			public void accept(FieldSource<JavaClassSource> javaClassSourceFieldSource) {
				System.out.println(javaClassSourceFieldSource.getType().getQualifiedName());
			}
		});
	}

	@Test
	public void buildMetadataClassTest() throws Exception {
		MetadataGenerator2.generate(JAVA_SOURCE.toFile(), Sets.newHashSet(User.class));
	}
}


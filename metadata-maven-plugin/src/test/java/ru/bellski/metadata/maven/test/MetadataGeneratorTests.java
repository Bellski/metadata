package ru.bellski.metadata.maven.test;

import com.google.common.collect.Sets;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.junit.Test;
import ru.bellski.metadata.maven.GenerateMetadataCompiler;
import ru.bellski.metadata.maven.MetadataGenerator;
import ru.bellski.metadata.maven.MetadataGenerator2;
import ru.bellski.metadata.maven.anewone.SQLMetadataGenerator;
import ru.bellski.metadata.maven.test.domain.Address;
import ru.bellski.metadata.maven.test.domain.Person;
import ru.bellski.metadata.maven.test.domain.PersonMetadata;
import ru.bellski.metadata.maven.test.domain.User;
import ru.bellski.metadata.maven.test.domain2.UserMetadata;
import ru.bellski.metadata.unmarshaller.SQLUnmarshaler;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//	@Test
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
        new MetadataGenerator2(JAVA_SOURCE.toFile(), Sets.newHashSet(User.class, Address.class, Person.class))
				.generate();
	}

    @Test
    public void testCl() throws Exception {

        List<Map<String, Object>> datas = new ArrayList<>();

        Map<String, Object> person = new HashMap<>();
        person.put(PersonMetadata.person.name.getName(), "A");

        datas.add(person);

        Map<String, Object> person1 = new HashMap<>();
        person1.put(PersonMetadata.person.name.getName(), "B");
        person1.put(PersonMetadata.person.parentPerson.getName(), "A");
        datas.add(person1);

        System.out.println(SQLUnmarshaler.unmarshallTree(datas, PersonMetadata.person, PersonMetadata.person.name));
    }

	@Test
	public void sqlMetageneratorTest() throws Exception {
		new SQLMetadataGenerator(null, Sets.newHashSet(UserMetadata.user)).generaate();
	}
}


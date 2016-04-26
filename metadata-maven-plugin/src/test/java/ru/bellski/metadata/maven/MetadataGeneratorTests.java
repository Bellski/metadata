package ru.bellski.metadata.maven;

import com.google.common.collect.Sets;
import org.junit.Test;
import ru.bellski.metadata.maven.domain.Address;
import ru.bellski.metadata.maven.domain.User;

import java.util.logging.Logger;

/**
 * Created by oem on 4/26/16.
 */
public class MetadataGeneratorTests {
	@Test
	public void metadataGenerationTest() throws Exception {
		MetamodelGenerator.generate(
			Logger.getAnonymousLogger(),
			"/home/oem/My/programming/projects/metadata/metadata-maven-plugin/src/test/java",
			Sets.newHashSet(User.class, Address.class)
		);
	}
}

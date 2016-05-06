package ru.bellski.metadata.maven.anewone;

import ru.bellski.metadata.anewone.MetaProperty;
import ru.bellski.metadata.anewone.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 5/6/16.
 */
public class SQLMetadataGenerator {
	private final File src;
	private final Set<Metadata<?>> metadatas;

	public SQLMetadataGenerator(File src, Set<Metadata<?>> metadatas) {
		this.src = src;
		this.metadatas= metadatas;
	}

	public void generaate() throws IOException {
		for (Metadata<?> metadata : metadatas) {
			final Set<String> paths = new HashSet<>();
			for (MetaProperty<?,?> metaProperty : metadata.getProperties()) {
				if (metaProperty.isNested()) {
					for (MetaProperty property : metaProperty.getMetadata().getProperties()) {
						paths.add(
							walkNestedProperties(
								metaProperty,
								new StringBuilder().append(metaProperty.getName()).append(".").append(property.getName())
							)
						);
					}
				} else {
					paths.add(metaProperty.getName());
				}
			}

			System.out.println(paths);
		}
	}

	String walkNestedProperties(MetaProperty<?,?> nProperty, StringBuilder paths) {
		if (nProperty.isNested()) {
			for (MetaProperty metaProperty : nProperty.getMetadata().getProperties()) {
				walkNestedProperties(
					metaProperty,
					new StringBuilder().append(metaProperty.getName()).append(".").append(metaProperty.getName())
				);
			}
		}

		return paths.toString();
	}


}

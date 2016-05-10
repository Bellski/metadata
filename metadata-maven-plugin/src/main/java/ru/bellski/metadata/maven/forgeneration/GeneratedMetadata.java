package ru.bellski.metadata.maven.forgeneration;

import ru.bellski.metadata.AbstractMetadata;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;

/**
 * Created by oem on 5/10/16.
 */
public class GeneratedMetadata extends AbstractMetadata implements Metadata {
	private final Class<?> type;

	public GeneratedMetadata(Class<?> type) {
		this.type = type;
	}

	@Override
	public Object create() {
		return null;
	}

	@Override
	public Class<?> getType() {
		return type;
	}

	public void addProperty(MetaProperty property) {
		addProperties();
	}
}

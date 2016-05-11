package ru.bellski.metadata.maven.forgeneration;

import ru.bellski.metadata.AbstractMetadata;
import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;

/**
 * Created by oem on 5/10/16.
 */
public class GeneratedMetadata<TYPE> extends AbstractMetadata<TYPE> implements Metadata<TYPE> {
	private final Class<TYPE> type;

	public GeneratedMetadata(Class<TYPE> type) {
		this.type = type;
	}

	@Override
	public TYPE create() {
		return null;
	}

	@Override
	public Class<TYPE> getType() {
		return type;
	}

	public void addProperty(MetaProperty<?, ?> property) {
		addProperties(property);
	}
}

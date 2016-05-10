package ru.bellski.metadata.maven.forgeneration;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;

/**
 * Created by oem on 5/10/16.
 */
public class GenerateMetaProperty implements MetaProperty {

	private final String name;
	private final Class type;
	private final boolean isNested;
	private final Metadata metadata;

	public GenerateMetaProperty(String name, Class type, boolean isNested, Metadata metadata) {
		this.name = name;
		this.type = type;
		this.isNested = isNested;
		this.metadata = metadata;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class getType() {
		return type;
	}

	@Override
	public void setValue(Object o, Object value) {

	}

	@Override
	public Object getValue(Object o) {
		return null;
	}

	@Override
	public boolean isNested() {
		return isNested;
	}

	@Override
	public Metadata getMetadata() {
		return metadata;
	}
}

package ru.bellski.metadata;

import java.util.Collection;
import java.util.Map;

/**
 * Created by oem on 4/26/16.
 */
public interface Metadata<TYPE> {
	Collection<MetaProperty> getProperties();

	boolean isPropertyExists(String name);

	MetaProperty getProperty(String name);
	TYPE createTYPE();

	Metadata getMetadataByProperty(MetaProperty metaProperty);

	boolean isNestedProperty(MetaProperty property);
}

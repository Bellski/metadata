package ru.bellski.metadata.yet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oem on 5/4/16.
 */
public abstract class AbstractMetadata<TYPE> implements Metadata<TYPE> {
	protected Map<String, MetaProperty> propertyMap= new HashMap<>();

	protected void addProperties(MetaProperty... metaProperty) {
		for (MetaProperty property : metaProperty) {
			propertyMap.put(property.getName(), property);
		}
	}
}

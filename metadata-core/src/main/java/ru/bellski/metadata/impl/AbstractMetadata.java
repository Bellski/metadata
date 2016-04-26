package ru.bellski.metadata.impl;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;
import ru.bellski.metadata.NestedMetaProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author bellski
 */
public abstract class AbstractMetadata<TYPE> implements Metadata<TYPE> {
	private final Map<String, MetaProperty> propertyByName = new HashMap<>();
	private Collection<MetaProperty> properties;

	private final Map<String, MetaProperty> nestedPropertyByName = new HashMap<>();
	private Collection<MetaProperty> nestedProperties;

	private final Map<MetaProperty, Metadata> metadataByProperty = new HashMap<>();

	protected AbstractMetadata() {
	}

	protected void addProperties(MetaProperty... properties) {
		for (final MetaProperty property : properties) {

			if (property instanceof NestedMetaProperty) {
				final NestedMetaProperty nestedProperty = (NestedMetaProperty) property;
				nestedProperty
					.getMetadata()
					.getProperties()
					.forEach(new Consumer<MetaProperty>() {
						@Override
						public void accept(MetaProperty nProperty) {
							metadataByProperty.put(property, nestedProperty.getMetadata());
							nestedPropertyByName.put(nProperty.getName(), property);
						}
					});
			}

			propertyByName.put(property.getName(), property);
		}

		this.properties = propertyByName.values();
		this.nestedProperties = nestedPropertyByName.values();
	}

	@Override
	public Collection<MetaProperty> getProperties() {
		return properties;
	}

	@Override
	public boolean isPropertyExists(String name) {
		return propertyByName.containsKey(name) || nestedPropertyByName.containsKey(name);
	}

	@Override
	public MetaProperty getProperty(String name) {
		MetaProperty property = propertyByName.get(name);

		if (property == null) {
			property = nestedPropertyByName.get(name);
		}

		return property;
	}

	@Override
	public Metadata getMetadataByProperty(MetaProperty metaProperty) {
		return metadataByProperty.get(metaProperty);
	}

	@Override
	public boolean isNestedProperty(MetaProperty property) {
		return metadataByProperty.containsKey(property);
	}

}

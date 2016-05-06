package ru.bellski.metadata.anewone;

/**
 * Created by oem on 5/4/16.
 */
public interface NestedMetaProperty<METADATA_TYPE, PROPERTY_TYPE, NESTED_PROPERTIES> extends MetaProperty<METADATA_TYPE, PROPERTY_TYPE> {
	NESTED_PROPERTIES nested();
}

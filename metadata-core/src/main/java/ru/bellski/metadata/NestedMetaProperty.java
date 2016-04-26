package ru.bellski.metadata;

/**
 * Created by oem on 4/26/16.
 */
public interface NestedMetaProperty<METADATA_TYPE, PROPERTY_TYPE> extends MetaProperty<METADATA_TYPE, PROPERTY_TYPE> {
	PROPERTY_TYPE create();
	Metadata getMetadata();
}

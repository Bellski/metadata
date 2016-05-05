package ru.bellski.metadata.yet;

/**
 * @author bellski
 */
public interface MetaProperty<METADATA_TYPE, PROPERTY_TYPE> {
	String getName();
	Class<PROPERTY_TYPE> getType();
	void setValue(METADATA_TYPE metadataType, PROPERTY_TYPE value);
	PROPERTY_TYPE getValue(METADATA_TYPE metadataType);
	boolean isNested();
	Metadata getMetadata();
}

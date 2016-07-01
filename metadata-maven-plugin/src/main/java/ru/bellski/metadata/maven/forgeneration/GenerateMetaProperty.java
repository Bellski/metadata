package ru.bellski.metadata.maven.forgeneration;

import ru.bellski.metadata.MetaProperty;
import ru.bellski.metadata.Metadata;

/**
 * Created by oem on 5/10/16.
 */
public class GenerateMetaProperty<METADATA_TYPE, PROPERTY_TYPE> implements MetaProperty<METADATA_TYPE, PROPERTY_TYPE> {

    private final String name;
    private final Class<PROPERTY_TYPE> type;
    private final boolean isNested;
    private final Class<METADATA_TYPE> metadataType;
    private Metadata<PROPERTY_TYPE> metadata;

    public GenerateMetaProperty(String name, Class<PROPERTY_TYPE> type, Class<METADATA_TYPE> metadataType, boolean isNested) {
        this.name = name;
        this.type = type;
        this.metadataType = metadataType;
        this.isNested = isNested;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<PROPERTY_TYPE> getType() {
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
    public Metadata<PROPERTY_TYPE> getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata<PROPERTY_TYPE> metadata) {
        this.metadata = metadata;
    }
}

package ru.bellski.metadata.impl;

import ru.bellski.metadata.NestedMetaProperty;

/**
 * Created by Aleksandr on 01.05.2016.
 */
public abstract class AbstractNestedMetadata<METADATA_TYPE, PROPERTY_TYPE> extends AbstractMetaPropertyImpl<METADATA_TYPE, PROPERTY_TYPE> implements NestedMetaProperty<METADATA_TYPE, PROPERTY_TYPE> {
    public AbstractNestedMetadata(String name, Class<PROPERTY_TYPE> type) {
        super(name, type);
    }
}

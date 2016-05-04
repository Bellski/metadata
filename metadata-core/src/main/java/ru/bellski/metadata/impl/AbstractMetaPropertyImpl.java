package ru.bellski.metadata.impl;

import ru.bellski.metadata.MetaProperty;

/**
 * Created by Aleksandr on 01.05.2016.
 */
public abstract class AbstractMetaPropertyImpl<METADATA_TYPE, PROPERTY_TYPE>  implements MetaProperty<METADATA_TYPE, PROPERTY_TYPE> {
    private final String name;
    private final String simpleName;
    private final Class<PROPERTY_TYPE> type;

    public AbstractMetaPropertyImpl(String name, Class<PROPERTY_TYPE> type) {
        this.simpleName = name;
        this.name = type.getSimpleName() + "." + name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSimpleName() {
        return simpleName;
    }

    @Override
    public Class<PROPERTY_TYPE> getType() {
        return type;
    }
}

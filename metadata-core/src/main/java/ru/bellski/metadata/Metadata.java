package ru.bellski.metadata;

import java.util.Collection;

/**
 * Created by oem on 5/4/16.
 */
public interface Metadata<TYPE> {
    TYPE create();

    Collection<MetaProperty<?, ?>> getProperties();

    boolean containsProperty(String name);

    MetaProperty<?, ?> getProperty(String name);

    Class<TYPE> getType();
}

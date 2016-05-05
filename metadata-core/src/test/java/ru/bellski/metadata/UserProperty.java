package ru.bellski.metadata;

/**
 * Created by oem on 5/4/16.
 */
public interface UserProperty<AddressProperties> extends ru.bellski.metadata.yet.MetaProperty {
	AddressProperties nested();
}

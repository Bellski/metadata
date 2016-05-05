package ru.bellski.metadata;

/**
 * Created by oem on 5/4/16.
 */
public interface AddressProperties<PROPERTY> {
	PROPERTY country();

	PROPERTY street();

	PROPERTY apartments();

	PROPERTY permanentAddress();
}

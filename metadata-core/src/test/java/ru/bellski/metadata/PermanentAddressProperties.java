package ru.bellski.metadata;

/**
 * Created by oem on 5/5/16.
 */
public interface PermanentAddressProperties<PROPERTY>  {
	PROPERTY country();

	PROPERTY street();

	PROPERTY apartments();
}

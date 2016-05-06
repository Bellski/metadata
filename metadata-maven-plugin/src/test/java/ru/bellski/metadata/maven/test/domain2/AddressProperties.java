package ru.bellski.metadata.maven.test.domain2;

/**
 * Created by oem on 5/4/16.
 */
public interface AddressProperties<PROPERTY> {
	PROPERTY country();

	PROPERTY street();

	PROPERTY apartments();

	PROPERTY permanentAddress();
}

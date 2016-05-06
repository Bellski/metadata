package ru.bellski.metadata.maven.test.domain2;

/**
 * Created by oem on 5/5/16.
 */
public interface PermanentAddressProperties<PROPERTY>  {
	PROPERTY country();

	PROPERTY street();

	PROPERTY apartments();
}

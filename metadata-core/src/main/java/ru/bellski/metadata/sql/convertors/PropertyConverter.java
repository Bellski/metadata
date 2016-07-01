package ru.bellski.metadata.sql.convertors;

/**
 * Created by oem on 7/1/16.
 */
public interface PropertyConverter<FROM, TO> {
	TO convert(FROM from);
}

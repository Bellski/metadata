package ru.bellski.metadata.maven;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by oem on 4/26/16.
 */
public enum Templates {
	META_CLASS("metadataClass"),
	META_PROPERTY("metaProperty"),
	NESTED_META_PROPERTY("nestedMetaProperty");

	public final String template;

	Templates(String name)  {
		try {
			template = CharStreams.toString(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(name)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

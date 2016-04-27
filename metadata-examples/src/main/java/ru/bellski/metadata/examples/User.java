package ru.bellski.metadata.examples;

import ru.bellski.metadata.annotaion.GenerateMetadata;

/**
 * Created by oem on 4/27/16.
 */
@GenerateMetadata
public class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

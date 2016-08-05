package org.vaadin.rise.codegen.light.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 8/5/16.
 */
public class NamePlacesData {
	public static final NamePlacesData INSTANCE = new NamePlacesData();

	private final Set<String> namePlaces = new HashSet<>();

	private NamePlacesData() {
	}

	public void addNamePlace(String namePlace) {
		namePlaces.add(namePlace);
	}

	public Set<String> getNamePlaces() {
		return namePlaces;
	}
}

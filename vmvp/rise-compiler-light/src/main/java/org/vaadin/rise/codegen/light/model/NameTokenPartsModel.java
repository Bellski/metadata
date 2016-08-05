package org.vaadin.rise.codegen.light.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 7/28/16.
 */
public class NameTokenPartsModel extends JFOModel {
	private Set<String> placeNames = new HashSet<>();

	public NameTokenPartsModel() {
		super("NameTokenParts");
	}

	public void addPlaces(Set<String> places) {
		for (String placeName : places) {
			for (String namePlacePart : placeName.split("/")) {
				this.placeNames.add(namePlacePart);
			}
		}
	}

	public Set<String> getPlaceNames() {
		return placeNames;
	}
}

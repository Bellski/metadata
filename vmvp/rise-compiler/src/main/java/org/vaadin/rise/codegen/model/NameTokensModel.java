package org.vaadin.rise.codegen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 7/28/16.
 */
public class NameTokensModel extends JFOModel {
	private Set<String> placeNames = new HashSet<>();

	public NameTokensModel(String packageName, Set<String> placeNames) {
		super("NameTokenParts", "NameTokenParts", packageName);

		for (String placeName : placeNames) {
			for (String namePlacePart : placeName.split("/")) {
				this.placeNames.add(namePlacePart);
			}
		}
	}

	public Set<String> getPlaceNames() {
		return placeNames;
	}
}

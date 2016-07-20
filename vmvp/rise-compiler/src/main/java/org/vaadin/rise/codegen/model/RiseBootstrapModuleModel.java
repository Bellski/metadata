package org.vaadin.rise.codegen.model;

import org.vaadin.rise.codegen.model.JFOModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 7/18/16.
 */
public class RiseBootstrapModuleModel extends JFOModel {
	private Set<String> places = new HashSet<>();

	public RiseBootstrapModuleModel(Set<String> places, String packageName) {
		super("RiseBootstrapModule", "RiseBootstrapModule",  packageName);
		this.places = places;
	}

	public Set<String> getPlaces() {
		return places;
	}
}

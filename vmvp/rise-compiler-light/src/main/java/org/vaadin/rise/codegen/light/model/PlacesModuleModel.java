package org.vaadin.rise.codegen.light.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oem on 8/5/16.
 */
public class PlacesModuleModel extends JFOModel {
	private List<PlacePresenterModel> presenters = new ArrayList<>();

	public PlacesModuleModel() {
		super("PlacesModule");
	}

	public void addPresenterModel(PlacePresenterModel placePresenterModel) {
		presenters.add(placePresenterModel);
		addImport(placePresenterModel);
		addImport(placePresenterModel.getPlaceImplementation());
	}

	public List<PlacePresenterModel> getPresenters() {
		return presenters;
	}
}

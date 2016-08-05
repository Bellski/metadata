package org.vaadin.rise.codegen.light.model;

import org.vaadin.rise.place.PresenterPlace;

/**
 * Created by oem on 8/5/16.
 */
public class PlacePresenterModel extends FqnHolder {
	private FqnHolder placeImplementation = new FqnHolder(PresenterPlace.class.getSimpleName(), PresenterPlace.class.getPackage().getName());
	private FqnHolder gateKeeper;
	private String placeName;

	public PlacePresenterModel(String presenterName, String presenterPackageName) {
		super(presenterName, presenterPackageName);
	}

	public FqnHolder getPlaceImplementation() {
		return placeImplementation;
	}

	public void setPlaceImplementation(FqnHolder placeImplementation) {
		this.placeImplementation = placeImplementation;
	}

	public FqnHolder getGateKeeper() {
		return gateKeeper;
	}

	public void setGateKeeper(FqnHolder gateKeeper) {
		this.gateKeeper = gateKeeper;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getPlaceNameWithoutHashBang() {
		return placeName.substring(2, placeName.length());
	}
}

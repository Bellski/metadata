package org.vaadin.rise.place;

import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;

import java.util.Map;
import java.util.Set;

/**
 * Created by oem on 7/29/16.
 */
public class PlaceDirectory {
	private final Map<String, Place> placeMap;
	private final Set<String> nameTokens;
	private final PlaceBus placeBus;

	public PlaceDirectory(Map<String, Place> placeMap, Set<String> nameTokens, PlaceBus placeBus) {
		this.placeMap = placeMap;
		this.nameTokens = nameTokens;
		this.placeBus = placeBus;
	}


}

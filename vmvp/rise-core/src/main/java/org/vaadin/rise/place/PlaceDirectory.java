package org.vaadin.rise.place;

import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;
import org.vaadin.rise.place.util.PlaceUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by oem on 7/29/16.
 */
public class PlaceDirectory {
	private final Map<String, Place> placeMap;
	private Set<String> nameTokens;
	private final PlaceBus placeBus;

	public PlaceDirectory(Map<String, Place> placeMap, Set<String> nameTokens, PlaceBus placeBus) {
		this.placeMap = placeMap;
		this.nameTokens = nameTokens;
		this.placeBus = placeBus;
	}

	public Place getPlace(PlaceRequest request) {
		return getPlace(request.getNameToken());
	}

	public Place getPlace(String namePlace) {
		namePlace = PlaceUtils.convertToPlaceString(namePlace, nameTokens);

		Place place = placeMap.get(namePlace);

		if (placeBus != null && place == null) {
			place = placeBus.getPlace(namePlace);
		}

		return place;
	}



}

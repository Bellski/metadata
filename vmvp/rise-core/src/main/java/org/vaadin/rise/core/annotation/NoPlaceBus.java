package org.vaadin.rise.core.annotation;

import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;

/**
 * Created by oem on 7/27/16.
 */
public class NoPlaceBus implements PlaceBus{
	@Override
	public Place getPlace(String compareToPlace) {
		return null;
	}
}

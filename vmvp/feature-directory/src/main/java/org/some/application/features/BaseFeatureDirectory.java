package org.some.application.features;

import org.some.application.features.api.FeatureDirectory;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;

/**
 * Created by oem on 7/27/16.
 */
public class BaseFeatureDirectory implements FeatureDirectory, PlaceBus {


	@Override
	public Place<RisePresenterImpl<?>> getPlace(String compareToPlace) {
		return null;
	}
}

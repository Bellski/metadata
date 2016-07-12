package org.vaadin.rise.place;

import dagger.Module;

/**
 * Created by oem on 7/12/16.
 */
@Module
public class PlaceManagerModule {
	private final String defaultPlace;
	private final String errorPlace;
	private final String unauthorizedPlace;

	public PlaceManagerModule(String defaultPlace, String errorPlace, String unauthorizedPlace) {

		this.defaultPlace = defaultPlace;
		this.errorPlace = errorPlace;
		this.unauthorizedPlace = unauthorizedPlace;
	}

	PlaceManager providesPlaceManager(PlaceManagerImpl placeManager) {
		return placeManager;
	}
}

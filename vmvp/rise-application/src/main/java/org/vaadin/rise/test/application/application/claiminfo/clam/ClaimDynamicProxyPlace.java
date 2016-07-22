package org.vaadin.rise.test.application.application.claiminfo.clam;

import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.event.PlaceRequestInternalEvent;
import org.vaadin.rise.place.event.PlaceRequestInternalHandler;
import org.vaadin.rise.test.application.application.PlaceProxyRegistryImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/22/16.
 */
@Singleton
public class ClaimDynamicProxyPlace implements PlaceRequestInternalHandler {

	@Inject
	public ClaimDynamicProxyPlace(PlaceProxyRegistryImpl placeProxyRegistry,  DefaultPlaceManager placeManager) {
		placeProxyRegistry.addTokenPlace("!/claim/dynamic/dynamicPlace");

		placeManager.addPlaceRequestInternalHandler(this);
	}

	@Override
	public void onPlaceRequest(PlaceRequestInternalEvent event) {
		System.out.print(event.getRequest());
	}
}

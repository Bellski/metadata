package org.vaadin.rise.place;

import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;
import org.vaadin.rise.place.api.UriFragmentSource;
import org.vaadin.rise.place.deprecated.PlaceRequest;

import java.util.Map;
import java.util.Set;

/**
 * Created by oem on 7/27/16.
 */
public class DefaultPlaceManager extends BasePlaceManager {
	private final PlaceRequest defaultPlaceRequest;
	private final PlaceRequest errorPlaceRequest;
	private final PlaceRequest unauthorizedPlaceRequest;

	public DefaultPlaceManager(Map<String, Place> placeMap,
							   Set<String> nameTokens,
							   UriFragmentSource uriFragmentSource,
							   String defaultPlaceNameToken,
							   String errorPlaceNameToken,
							   String unauthorizedPlaceNameToken) {

		this(
			placeMap,
			nameTokens,
			null,
			uriFragmentSource,
			defaultPlaceNameToken,
			errorPlaceNameToken,
			unauthorizedPlaceNameToken
		);
	}

	public DefaultPlaceManager(Map<String, Place> placeMap,
							   Set<String> nameTokens,
							   PlaceBus placeBus,
							   UriFragmentSource uriFragmentSource,
							   String defaultPlaceNameToken,
							   String errorPlaceNameToken,
							   String unauthorizedPlaceNameToken) {

		super(placeMap, nameTokens, placeBus, uriFragmentSource);

		defaultPlaceRequest = new PlaceRequest.Builder().nameToken(defaultPlaceNameToken).build();
		errorPlaceRequest = new PlaceRequest.Builder().nameToken(errorPlaceNameToken).build();
		unauthorizedPlaceRequest = new PlaceRequest.Builder().nameToken(unauthorizedPlaceNameToken).build();
	}

	@Override
	public void revealDefaultPlace() {
		revealPlace(defaultPlaceRequest, false);
	}

	@Override
	public void revealErrorPlace(String invalidHistoryToken) {
		revealPlace(errorPlaceRequest, false);
	}

	@Override
	public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
		revealPlace(unauthorizedPlaceRequest, false);
	}
}
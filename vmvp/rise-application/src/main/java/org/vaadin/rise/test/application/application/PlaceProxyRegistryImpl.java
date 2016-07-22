package org.vaadin.rise.test.application.application;

import org.vaadin.rise.place.token.PlaceTokenRegistry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 7/22/16.
 */
@Singleton
public class PlaceProxyRegistryImpl implements PlaceTokenRegistry {
	private final Set<String> places = new HashSet<>(); {
		places.add("!/claiminfo");
		places.add("!/claimlist");
		places.add("!/error");
		places.add("!/claim/{claimId}");
	}

	@Inject
	public PlaceProxyRegistryImpl() {
	}

	@Override
	public Set<String> getAllPlaceTokens() {
		return places;
	}

	public void addTokenPlace(String dynamicPlace) {
		places.add(dynamicPlace);
	}
}

package javasource;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EntryPlaceBus implements PlaceBus {

	@Inject
	public EntryPlaceBus() {
	}

	@Override
	public Place getPlace(String compareToPlace) {
		return null;
	}

	@Override
	public boolean containsPlace(String namePlace) {
		return false;
	}
}
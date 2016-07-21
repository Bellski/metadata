package javasource;

import javax.inject.Inject;

import javasource.SubEntryGateKeeper;
import javasource.SubEntryPresenter;


import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

import org.vaadin.rise.place.PlaceWithGatekeeper;
import org.vaadin.rise.security.Gatekeeper;

import org.vaadin.rise.proxy.LazyPresenter;


public class RiseSubEntryPresenterProxy extends ProxyPlaceImpl<SubEntryPresenter> {

	@Inject
	public RiseSubEntryPresenterProxy(SubEntryGateKeeper gateKeeper, DefaultPlaceManager placeManager, LazyPresenter<SubEntryPresenter> lazyPresenter) {
		super(new PlaceWithGatekeeper("sub", gateKeeper), placeManager, lazyPresenter);
	}
}

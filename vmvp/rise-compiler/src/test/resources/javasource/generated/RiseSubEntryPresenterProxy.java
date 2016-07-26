package javasource;

import javax.inject.Inject;


import org.vaadin.rise.place.deprecated.DefaultPlaceManager_TODO_TODO;
import org.vaadin.rise.deprecated.proxy.ProxyPlaceImpl;

import org.vaadin.rise.place.deprecated.PlaceWithGatekeeper;

import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;


public class RiseSubEntryPresenterProxy extends ProxyPlaceImpl<SubEntryPresenter> {

	@Inject
	public RiseSubEntryPresenterProxy(SubEntryGateKeeper gateKeeper, DefaultPlaceManager_TODO_TODO placeManager, LazyPlacePresenter<SubEntryPresenter> lazyPresenter) {
		super(new PlaceWithGatekeeper("sub", gateKeeper), placeManager, lazyPresenter);
	}
}

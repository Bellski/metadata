package javasource;

import javax.inject.Inject;


import org.vaadin.rise.place.deprecated.DefaultPlaceManager_TODO_TODO;
import org.vaadin.rise.place.deprecated.PlaceImpl;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.deprecated.proxy.ProxyPlaceImpl;


public class RiseSubSubEntryPresenterProxy extends ProxyPlaceImpl<SubSubEntryPresenter> {

	@Inject
	public RiseSubSubEntryPresenterProxy(DefaultPlaceManager_TODO_TODO placeManager, LazyPlacePresenter<SubSubEntryPresenter> lazyPresenter) {
		super(new PlaceImpl("subsub"), placeManager, lazyPresenter);
	}
}
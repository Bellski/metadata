package javasource;

import dagger.Lazy;
import javax.inject.Inject;

import javasource.SubEntryPresenter

import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

public class RiseSubEntryPresenterProxy extends ProxyPlaceImpl<SubEntryPresenter> {

	@Inject
	public RiseSubEntryPresenterProxy(DefaultPlaceManager placeManager, Lazy<SubEntryPresenter> lazyPresenter) {
		super(new PlaceImpl("sub"), placeManager, lazyPresenter);
	}
}

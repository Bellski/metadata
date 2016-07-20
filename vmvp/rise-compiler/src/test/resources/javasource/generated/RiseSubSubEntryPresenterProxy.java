package javasource;

import dagger.Lazy;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

import javax.inject.Inject;

public class RiseSubSubEntryPresenterProxy extends ProxyPlaceImpl<SubSubEntryPresenter> {

	@Inject
	public RiseSubSubEntryPresenterProxy(DefaultPlaceManager placeManager, Lazy<SubSubEntryPresenter> lazyPresenter) {
		super(new PlaceImpl("subsub"), placeManager, lazyPresenter);
	}
}
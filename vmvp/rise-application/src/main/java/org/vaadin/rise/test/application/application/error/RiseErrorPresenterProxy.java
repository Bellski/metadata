package org.vaadin.rise.test.application.application.error;

import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.LazyPresenter;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

import javax.inject.Inject;

public class RiseErrorPresenterProxy extends ProxyPlaceImpl<ErrorPresenter> {

    @Inject
    public RiseErrorPresenterProxy(DefaultPlaceManager placeManager, LazyPresenter<ErrorPresenter> lazyPresenter) {
        super(new PlaceImpl("!/error"), placeManager, lazyPresenter);
    }
}


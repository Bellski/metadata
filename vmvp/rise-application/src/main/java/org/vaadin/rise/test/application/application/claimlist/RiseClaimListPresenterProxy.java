package org.vaadin.rise.test.application.application.claimlist;

import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.LazyPresenter;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

import javax.inject.Inject;

public class RiseClaimListPresenterProxy extends ProxyPlaceImpl<ClaimListPresenter> {

    @Inject
    public RiseClaimListPresenterProxy(DefaultPlaceManager placeManager, LazyPresenter<ClaimListPresenter> lazyPresenter) {
        super(new PlaceImpl("!/claimlist"), placeManager, lazyPresenter);
    }
}


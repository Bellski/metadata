package org.vaadin.rise.test.application.application.claiminfo;

import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.LazyPresenter;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

import javax.inject.Inject;

public class RiseClaimInfoPresenterProxy extends ProxyPlaceImpl<ClaimInfoPresenter> {

    @Inject
    public RiseClaimInfoPresenterProxy(DefaultPlaceManager placeManager, LazyPresenter<ClaimInfoPresenter> lazyPresenter) {
        super(new PlaceImpl("!/claiminfo"), placeManager, lazyPresenter);
    }
}


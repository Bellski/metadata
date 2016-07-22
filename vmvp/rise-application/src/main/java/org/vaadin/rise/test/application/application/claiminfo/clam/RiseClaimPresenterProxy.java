package org.vaadin.rise.test.application.application.claiminfo.clam;

import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.LazyPresenter;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

import javax.inject.Inject;

public class RiseClaimPresenterProxy extends ProxyPlaceImpl<ClaimPresenter> {

    @Inject
    public RiseClaimPresenterProxy(ClaimDynamicProxyPlace claimDynamicProxyPlace, DefaultPlaceManager placeManager, LazyPresenter<ClaimPresenter> lazyPresenter) {
        super(new PlaceImpl("!/claim/{claimId}"), placeManager, lazyPresenter);
    }
}


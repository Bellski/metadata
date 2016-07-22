
package org.vaadin.rise.test.application.application;

import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.place.DefaultPlaceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
* Created by oem on 7/12/16.
*/
@Singleton
public class Bootstrap {
    @Inject
    DefaultPlaceManager placeManager;

    @Inject
    EagerProxies eagerProxies;

    @Inject
    RootPresenter.RootSlot rootSlot;

    @Inject
    public Bootstrap() {
    }

    public void doBootstrap() {
        placeManager.revealCurrentPlace();
    }
}
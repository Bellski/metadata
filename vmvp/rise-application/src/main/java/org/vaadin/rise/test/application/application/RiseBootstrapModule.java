
package org.vaadin.rise.test.application.application;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.core.Root;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.RootView;
import org.vaadin.rise.place.token.PlaceTokenRegistry;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;


@Module(includes = {VaadinModule.class, PlaceManagerModule.class})
public class RiseBootstrapModule {

    @Provides @Singleton
    PlaceTokenRegistry providesPlaceTokenRegistry(PlaceProxyRegistryImpl  placeProxyRegistry) {
        return placeProxyRegistry;
    }

    @Provides @Singleton
    RootPresenter.RootSlot providesRootSlot(RootPresenter.RiseRootSlot rootSlot) {
        return rootSlot;
    }

    @Provides @Singleton
    Root.Presenter providesRootPresenter(RootPresenter rootPresenter) {
        return rootPresenter;
    }

    @Provides @Singleton
    Root.View providesRootView(RootView rootView) {
        return rootView;
    }
}

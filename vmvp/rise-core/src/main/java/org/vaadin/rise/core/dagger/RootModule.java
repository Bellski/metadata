package org.vaadin.rise.core.dagger;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.core.Root;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.RootView;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module
public class RootModule {
    @Provides
    @Singleton
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

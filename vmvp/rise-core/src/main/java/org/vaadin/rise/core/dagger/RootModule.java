package org.vaadin.rise.core.dagger;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.Root;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.RootView;
import org.vaadin.rise.place.LazyPresenterProvider;
import org.vaadin.rise.slot.SlotRevealBus;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module
public class RootModule {

    @Provides @Singleton
    Root.Presenter providesRootPresenter(RootPresenter rootPresenter) {
        return rootPresenter;
    }

    @Provides @Singleton
    Root.View providesRootView(RootView rootView) {
        return rootView;
    }

    @Provides
    @Singleton
    @IntoSet
    public static LazyPresenterProvider<?> lazyPresenterProvider(Lazy<RootPresenter> presenterLazy, SlotRevealBus slotRevealBus) {
        final LazyPresenterProvider<?> lazyPresenterProvider = new LazyPresenterProvider<>(presenterLazy);

        slotRevealBus.registerSlot(RootPresenter.ROOT_SLOT, lazyPresenterProvider);

        return lazyPresenterProvider;
    }
}

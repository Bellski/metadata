package org.vaadin.rise.slot;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.LazyPresenterProvider;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Module
public class SlotsModule {

    @Provides
    @Singleton
    static SlotRevealBus slotRevealBus() {
        return new SlotRevealBus();
    }

    static Set<LazyPresenterProvider<?>> lazyPresenterProviders() {
        return new HashSet<>();
    }
}

package org.vaadin.rise2.test.dummy.root;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.Root;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.RootView;
import org.vaadin.rise.place.LazyPresenterProvider;
import org.vaadin.rise.slot.SlotRevealBus;
import org.vaadin.rise2.test.dummy.DummyUI;

/**
 * Created by Aleksandr on 07.08.2016.
 */
public class DummyRootModule {

    private static Root.View view() {
        return new RootView(new DummyUI());
    }

    private static Root.Presenter presenter() {
        return new RootPresenter(view());
    }

    public static LazyPresenterProvider<?> lazyPresenterProvider(SlotRevealBus slotRevealBus) {
        final LazyPresenterProvider<?> lazyPresenterProvider = new LazyPresenterProvider<>(new Lazy<RisePresenterImpl<?>>() {
            @Override
            public RisePresenterImpl<?> get() {
                return (RisePresenterImpl<?>) presenter();
            }
        });

        slotRevealBus.registerSlot(RootPresenter.ROOT_SLOT, lazyPresenterProvider);

        return lazyPresenterProvider;
    }
}

package org.vaadin.rise.slot;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.LazyPresenterProvider;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Singleton
public class SlotRevealBus {
    private Map<NestedSlot, LazyPresenterProvider<?>> providerMap = new HashMap<>();

    @Inject
    public SlotRevealBus() {
    }

    public void registerSlot(NestedSlot slot, LazyPresenterProvider<?> presenterProvider) {
        providerMap.put(slot, presenterProvider);
    }

    public void fireReveal(NestedSlot slot, RisePresenterImpl<?> child) {
        final LazyPresenterProvider<?> presenterProvider = providerMap.get(slot);

        Objects.requireNonNull(presenterProvider, "Presenter provider not found by slot");

        final RisePresenterImpl<?> presenter = presenterProvider.getPresenter();
        presenter.forceReveal();

        presenter.setInSlot(slot, child);
    }
}
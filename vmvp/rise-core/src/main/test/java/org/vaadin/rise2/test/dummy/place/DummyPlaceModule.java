package org.vaadin.rise2.test.dummy.place;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.slot.SlotRevealBus;
import org.vaadin.rise2.test.dummy.DummyUI;

import java.util.Map;

/**
 * Created by Aleksandr on 07.08.2016.
 */
public class DummyPlaceModule {

    private static DummyView view() {
        return new DummyView(new DummyUI());
    }

    private static DummyPresenter presenter(SlotRevealBus slotRevealBus) {
        return new DummyPresenter(view(), slotRevealBus);
    }

    public Place place(SlotRevealBus slotRevealBus,
                              String nameToken,
                              String nameTokenWithHiddenParams,
                              String[] paramNames,
                              int[] paramIndexes,
                              Map<String, Place> stringPlaceMap) {

        final PresenterPlace<RisePresenterImpl<?>> pPlace = new PresenterPlace<>(
                () -> presenter(slotRevealBus),
                nameToken,
                nameTokenWithHiddenParams,
                paramNames,
                paramIndexes
        );

        stringPlaceMap.put(nameTokenWithHiddenParams, pPlace);

        return pPlace;
    }
}

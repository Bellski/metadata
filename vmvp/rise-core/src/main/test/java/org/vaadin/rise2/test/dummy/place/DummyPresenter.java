package org.vaadin.rise2.test.dummy.place;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.place.PlaceRequest;
import org.vaadin.rise.place.reveal.Supplier;
import org.vaadin.rise.slot.SlotRevealBus;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class DummyPresenter extends RisePresenterImpl<DummyView> {
    public boolean haveBeenPrepared = false;

    protected DummyPresenter(DummyView view, SlotRevealBus slotRevealBus) {
        super(view, slotRevealBus, RootPresenter.ROOT_SLOT);
    }


    @Override
    public void prepareFromRequest(PlaceRequest request, Supplier supplier) {
        haveBeenPrepared = true;
    }
}

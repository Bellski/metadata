package org.vaadin.rise2.test.dummy;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.deprecated.PlaceRequest;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class DummyPresenter extends RisePresenterImpl<DummyView> {
    public boolean haveBeenPrepared = false;

    public DummyPresenter(DummyView view, DummyRootSlot dummyRootSlot) {
        super(view, dummyRootSlot);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        haveBeenPrepared = true;
    }
}

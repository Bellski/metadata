package org.vaadin.rise2.test.dummy;

import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.BasePlace;
import org.vaadin.rise.place.PresenterPlace;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class DummyPlace extends PresenterPlace<DummyPresenter> {
    public DummyPlace(String placeName, LazyPlacePresenter<DummyPresenter> lazyPresenter) {
        super(PlaceDataBuilder.build(placeName), lazyPresenter);
    }
}

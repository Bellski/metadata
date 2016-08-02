package org.vaadin.rise2.test.dummy;

import org.vaadin.rise.place.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class DummyPlace extends PresenterPlace<DummyPresenter> {
    public DummyPlace(LazyPlacePresenter<DummyPresenter> lazyPresenter, String nameToken, String nameTokenWithHiddenParams, String[] paramNames, int[] paramIndexes) {
        super(lazyPresenter, nameToken, nameTokenWithHiddenParams, paramNames, paramIndexes);
    }
}

package org.vaadin.rise.place;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class PresenterPlace<Presenter_ extends RisePresenterImpl<?>> extends BasePlace {
    private final LazyPlacePresenter<Presenter_> lazyPresenter;

    public PresenterPlace(LazyPlacePresenter<Presenter_> lazyPresenter,
                          String nameToken,
                          String nameTokenWithHiddenParams,
                          String[] paramNames,
                          int[] paramIndexes) {

        super (
                nameToken,
                nameTokenWithHiddenParams,
                paramNames,
                paramIndexes
        );

        this.lazyPresenter = lazyPresenter;
    }

    @Override
    public RisePresenterImpl<?> reveal() {
        return lazyPresenter.getLazyPresenter();
    }
}

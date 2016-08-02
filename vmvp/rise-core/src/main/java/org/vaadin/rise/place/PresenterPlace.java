package org.vaadin.rise.place;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.reveal.RevealException;
import org.vaadin.rise.place.reveal.Supplier;

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
    public void reveal(PlaceRequest placeRequest, Supplier supplier) {
        try {
            final Presenter_ presenter = lazyPresenter.getLazyPresenter();
            presenter.prepareFromRequest(placeRequest, supplier);

            if (!presenter.useManualReveal()) {
                supplier.onSuccess(presenter);
            }

        } catch (Exception e) {
            supplier.onFailure(new RevealException(e));
        }
    }
}

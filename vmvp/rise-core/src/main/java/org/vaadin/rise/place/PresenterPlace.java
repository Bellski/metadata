package org.vaadin.rise.place;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.reveal.RevealException;
import org.vaadin.rise.place.reveal.Supplier;
import org.vaadin.rise.security.Gatekeeper;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class PresenterPlace<Presenter_ extends RisePresenterImpl<?>> extends LazyPresenterProvider<Presenter_> implements Place{
    protected String nameToken;
    protected String nameTokenWithHiddenParams;

    protected String[] paramNames;
    protected int[] paramIndexes;


    public PresenterPlace(Lazy<Presenter_> lazyPresenter,
                          String nameToken,
                          String nameTokenWithHiddenParams,
                          String[] paramNames,
                          int[] paramIndexes) {

        super (lazyPresenter);

        this.nameToken = nameToken;
        this.nameTokenWithHiddenParams = nameTokenWithHiddenParams;
        this.paramNames = paramNames;
        this.paramIndexes = paramIndexes;
    }


    @Override
    public String[] getParamNames() {
        return paramNames;
    }

    @Override
    public int[] getParamIndexes() {
        return paramIndexes;
    }

    @Override
    public boolean hasParameters() {
        return paramIndexes != null;
    }

    @Override
    public boolean canReveal() {
        return true;
    }

    @Override
    public String getFallBackNamePlace() {
        return null;
    }

    @Override
    public void replaceGateKeeper(Gatekeeper gatekeeper) {

    }

    @Override
    public void reveal(PlaceRequest placeRequest, Supplier supplier) {
        try {
            final Presenter_ presenter = getPresenter();
            presenter.prepareFromRequest(placeRequest, supplier);

            if (!presenter.useManualReveal()) {
                supplier.onSuccess(presenter);
            }

        } catch (Exception e) {
            supplier.onFailure(new RevealException(e));
        }
    }

    @Override
    public String getNameToken() {
        return nameToken;
    }

    @Override
    public String toString() {
        return nameToken;
    }
}

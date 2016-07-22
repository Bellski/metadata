package org.vaadin.rise.test.application.application;

import org.vaadin.rise.proxy.BaseProxy;
import org.vaadin.rise.proxy.LazyPresenter;

import javax.inject.Inject;

public class RiseCas1PresenterProxy extends BaseProxy<Cas1Presenter> {

    @Inject
    public RiseCas1PresenterProxy(LazyPresenter<Cas1Presenter> lazyPresenter) {
        super(lazyPresenter);
    }
}



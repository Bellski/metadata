package org.vaadin.rise.test.application.application.error;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.proxy.annotation.PlaceProxy;
import org.vaadin.rise.test.application.application.Cas1Presenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@PlaceProxy(placeName = "!error")
public class ErrorPresenter extends RisePresenterImpl<Error.View> implements Error.Presenter {

    @Inject
    protected ErrorPresenter(Error.View view, Cas1Presenter.Slot1 slot1) {
        super(view, slot1);
    }
}

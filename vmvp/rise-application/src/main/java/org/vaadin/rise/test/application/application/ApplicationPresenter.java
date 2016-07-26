package org.vaadin.rise.test.application.application;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class ApplicationPresenter extends RisePresenterImpl<Application.View> implements Application.Presenter  {

    @Inject
    protected ApplicationPresenter(Application.View view, RootPresenter.RootSlot rootSlot) {
        super(view, rootSlot);
    }
}

package org.vaadin.rise.test.application.application.all;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.place.deprecated.PlaceRequest;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class AllPresenter extends RisePresenterImpl<All.View> implements All.Presenter  {

    @Inject
    protected AllPresenter(All.View view, RootPresenter.RootSlot rootSlot) {
        super(view, rootSlot);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
    }
}

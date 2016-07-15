package org.vaadin.rise.test.application.application.claimlist;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.proxy.annotation.PlaceProxy;
import org.vaadin.rise.test.application.application.Cas1Presenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Presenter(placeName = "!claimlist")
public class ClaimListPresenter extends RisePresenterImpl<ClaimList.View> implements ClaimList.Presenter {

    @Inject
    protected ClaimListPresenter(ClaimList.View view, Cas1Presenter.Slot1 slot1) {
        super(view, slot1);
    }
}

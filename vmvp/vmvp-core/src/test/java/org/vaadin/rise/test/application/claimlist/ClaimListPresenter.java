package org.vaadin.rise.test.application.claimlist;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.proxy.annotation.PlaceProxy;
import org.vaadin.rise.proxy.slot.NestedSlot;
import org.vaadin.rise.test.application.Cas1Presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Singleton
@PlaceProxy(placeName = "!claimlist")
public class ClaimListPresenter extends RisePresenterImpl<ClaimList.View> implements ClaimList.Presenter {

    @Inject
    protected ClaimListPresenter(RiseEventBus eventBus, ClaimList.View view, Cas1Presenter.Slot1 nestedSlot) {
        super(eventBus, view, nestedSlot);

        System.out.println("YAHOOOO");
    }
}

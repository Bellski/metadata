package org.vaadin.rise.test.application.application.claiminfo;

import dagger.Lazy;
import org.vaadin.rise.proxy.slot.NestedSlot;

import javax.inject.Inject;


public class ClaimInfoPresenterSlot2 extends NestedSlot<ClaimInfoPresenter> implements ClaimInfoPresenter.Slot2 {

    @Inject
    public ClaimInfoPresenterSlot2(Lazy<ClaimInfoPresenter> presenter) {
        super(presenter);
    }
}

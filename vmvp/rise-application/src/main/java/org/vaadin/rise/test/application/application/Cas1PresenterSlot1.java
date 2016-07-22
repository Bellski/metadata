package org.vaadin.rise.test.application.application;

import dagger.Lazy;
import org.vaadin.rise.proxy.slot.NestedSlot;

import javax.inject.Inject;


public class Cas1PresenterSlot1 extends NestedSlot<Cas1Presenter> implements Cas1Presenter.Slot1 {

    @Inject
    public Cas1PresenterSlot1(Lazy<Cas1Presenter> presenter) {
        super(presenter);
    }
}

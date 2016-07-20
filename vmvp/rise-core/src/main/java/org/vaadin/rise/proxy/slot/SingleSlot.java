package org.vaadin.rise.proxy.slot;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.core.RisePresenterImpl;

public class SingleSlot<PRESENTER extends RisePresenterImpl<?>> extends Slot<PRESENTER> implements IsSingleSlot<PRESENTER>, RemovableSlot<PRESENTER> {


    public SingleSlot(Lazy<PRESENTER> presenter) {
        super(presenter);
    }

    @Override
    public boolean isPopup() {
        return false;
    }
    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public <PRESENTER_COMPONENT extends RisePresenterComponent<?>> void setContent(PRESENTER_COMPONENT content) {
        final PRESENTER presenter_ = presenter.get();
        presenter_.forceReveal();
        presenter_.setInSlot(this, (PRESENTER) content);
    }
}
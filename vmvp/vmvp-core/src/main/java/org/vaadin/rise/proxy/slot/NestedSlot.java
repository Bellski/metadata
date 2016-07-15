package org.vaadin.rise.proxy.slot;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;


public class NestedSlot<PRESENTER extends RisePresenterImpl<?>> extends SingleSlot<PRESENTER>  {

    public NestedSlot(Lazy<PRESENTER> presenter) {
        super(presenter);
    }
}
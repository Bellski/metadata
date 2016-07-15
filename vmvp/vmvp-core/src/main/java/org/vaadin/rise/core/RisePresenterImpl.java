package org.vaadin.rise.core;


import org.vaadin.rise.proxy.slot.IsNested;
import org.vaadin.rise.proxy.slot.NestedSlot;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
public class RisePresenterImpl<VIEW extends RiseView> extends RisePresenterComponent<VIEW> {

    private IsNested<?> slot;

    protected RisePresenterImpl(VIEW view) {
        this(view, null);
    }

    protected RisePresenterImpl(VIEW view, IsNested<?> nestedSlot) {
        super(view);
        this.slot = nestedSlot;
    }

    public final void forceReveal() {
        if (isVisible()) {
            return;
        }
        slot.setContent(this);
    }

    public boolean useManualReveal() {
        return false;
    }
}

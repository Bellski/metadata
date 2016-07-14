package org.vaadin.rise.core;


import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.proxy.slot.NestedSlot;
import org.vaadin.rise.proxy.slot.event.RevealContentEvent;
import org.vaadin.rise.proxy.slot.event.RevealRootContentEvent;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class RisePresenterImpl<VIEW extends RiseView> extends RisePresenterComponent<VIEW> {
    public enum RevealType {
        Root,
        RootPopup
    }

    private RevealType revealType;
    private NestedSlot slot;

    protected RisePresenterImpl(RiseEventBus eventBus, VIEW view, NestedSlot nestedSlot) {
        this(eventBus, view, nestedSlot, null);
    }

    protected RisePresenterImpl(RiseEventBus eventBus, VIEW view, RevealType revealType) {
        this(eventBus, view, null, revealType);
    }

    protected RisePresenterImpl(RiseEventBus eventBus, VIEW view, NestedSlot nestedSlot, RevealType revealType) {
        super(eventBus, view);
        this.revealType = revealType;
        this.slot = nestedSlot;
    }

    public final void forceReveal() {
        if (isVisible()) {
            return;
        }
        revealInParent();
    }

    public boolean useManualReveal() {
        return false;
    }

    protected void revealInParent() {
        if (revealType != null) {
            switch (revealType) {
                case Root:
                    RevealRootContentEvent.fire(this, this);
                    break;
//                case RootPopup:
//                    RevealRootPopupContentEvent.fire(this, (PresenterWidget<PopupView>) this);
//                    break;
            }
        } else {
            RevealContentEvent.fire(this, slot, this);
        }
    }
}

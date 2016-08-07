package org.vaadin.rise.core;


import org.vaadin.rise.place.PlaceRequest;
import org.vaadin.rise.slot.SlotRevealBus;
import org.vaadin.rise.slot.NestedSlot;
import org.vaadin.rise.place.reveal.Supplier;

/**
 * Created by oem on 7/12/16.
 */
public class RisePresenterImpl<VIEW extends RiseView> extends RisePresenterComponent<VIEW> {

    private final SlotRevealBus slotRevealBus;
    private final NestedSlot slot;

    public RisePresenterImpl(VIEW view) {
        this(view, null, null);
    }


    protected RisePresenterImpl(VIEW view, SlotRevealBus slotRevealBus, NestedSlot nestedSlot) {
        super(view);
        this.slotRevealBus = slotRevealBus;
        this.slot = nestedSlot;
    }

    public final void forceReveal() {
        if (isVisible()) {
            return;
        }

        if (slotRevealBus != null && slot != null) {
            slotRevealBus.fireReveal(slot, this);
        }
    }

    public boolean useManualReveal() {
        return false;
    }



    public void prepareFromRequest(PlaceRequest request, Supplier supplier) {

    }

}

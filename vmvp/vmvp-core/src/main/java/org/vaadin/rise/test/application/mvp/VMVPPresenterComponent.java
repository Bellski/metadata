package org.vaadin.rise.test.application.mvp;

import com.vaadin.ui.Component;
import org.vaadin.rise.test.application.mvp.slots.*;
import org.vaadin.rise.test.application.proxy.VMVPEventBus;
import org.vaadin.rise.test.application.vaadin.IsComponent;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by Aleksandr on 09.07.2016.
 */
public class VMVPPresenterComponent<VIEW extends VMVPView> implements VMVPPresenter<VIEW>, IsComponent, HasSlots {

    VMVPPresenterComponent<?> parent;
    IsSlot<?> slot;
    protected boolean visible;

    private final Set<VMVPPresenterComponent<?>> children = new HashSet<>();

    protected final VMVPEventBus eventBus;
    private final VIEW view;

    @Inject
    public VMVPPresenterComponent(VIEW view, VMVPEventBus eventBus) {
        this.view = view;
        this.eventBus = eventBus;
    }

    public VIEW getView() {
        return view;
    }

    @Override
    public Component asComponent() {
        return getView().asComponent();
    }

    @Override
    public <T extends VMVPPresenterComponent<?>> void addToSlot(MultiSlot<T> slot, T child) {
        assert child != null : "cannot add null to a slot";

        if (child.slot == slot && child.parent == this) {
            return;
        }


        if (!child.isPopup()) {
            getView().addToSlot(slot.getRawSlot(), child);
        }
        if (isVisible()) {
            child.internalReveal();
        }
    }

    @Override
    public void clearSlot(RemovableSlot<?> slot) {
        internalClearSlot(slot, null);
        getView().setInSlot(slot.getRawSlot(), null);
    }

    private void internalClearSlot(IsSlot<?> slot, VMVPPresenterComponent<?> dontRemove) {
        // use new set to prevent concurrent modification
        for (VMVPPresenterComponent<?> child: new HashSet<>(children)) {
            if (child.slot == slot && !child.equals(dontRemove)) {
                child.orphan();
            }
        }
    }

    @Override
    public <T extends VMVPPresenterComponent<?>> void removeFromSlot(RemovableSlot<T> slot, T child) {
        rawRemoveFromSlot(slot, child);
    }

    private void rawRemoveFromSlot(IsSlot<?> slot, VMVPPresenterComponent<?> child) {
        if (child == null || child.slot != slot) {
            return;
        }

        if (!child.isPopup()) {
            getView().removeFromSlot(slot.getRawSlot(), child);
        }

        child.orphan();
    }

    @Override
    public <T extends VMVPPresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child) {
        setInSlot(slot, child, true);
    }

    @Override
    public <T extends VMVPPresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child, boolean performReset) {
        if (child == null) {
            clearSlot((RemovableSlot<?>) slot);
            return;
        }

        adoptChild(slot, child);

        internalClearSlot(slot, child);

        if (!child.isPopup()) {
            getView().setInSlot(slot.getRawSlot(), child);
        }

        if (isVisible()) {
            child.internalReveal();
            if (performReset) {
//                ResetPresentersEvent.fire(this);
            }
        }
    }

    @Override
    public <T extends VMVPPresenterComponent<?>> T getChild(IsSingleSlot<T> slot) {
        Iterator<T> it = getSlotChildren(slot).iterator();
        return it.hasNext() ? it.next() : null;
    }

    @Override
    public <T extends VMVPPresenterComponent<?>> Set<T> getChildren(Slot<T> slot) {
        return getSlotChildren(slot);
    }

    @Override
    public <T extends VMVPPresenterComponent<?> & Comparable<T>> List<T> getChildren(OrderedSlot<T> slot) {
        List<T> result = new ArrayList<>(getSlotChildren(slot));
        Collections.sort(result);
        return result;
    }

    void internalReveal() {
        if (isVisible()) {
            return;
        }
        onReveal();
        visible = true;

        // use new set to prevent concurrent modification
        for (VMVPPresenterComponent<?> child: new HashSet<>(children)) {
            child.internalReveal();
        }

        if (isPopup()) {

        }

    }

    protected void onReveal() {
    }

    boolean isPopup() {
        return slot != null && slot.isPopup();
    }

    boolean isVisible() {
        return visible;
    }

    private <T extends VMVPPresenterComponent<?>> Set<T> getSlotChildren(IsSlot<T> slot) {
        Set<T> result = new HashSet<>();
        for (VMVPPresenterComponent<?> child : children) {
            if (child.slot == slot) {
                result.add((T) child);
            }
        }
        return result;
    }

    /**
     * Disconnects a child from its parent.
     */
    private void orphan() {
        if (slot == null) {
            return;
        }
        if (!slot.isRemovable()) {
            throw new IllegalArgumentException("Cannot remove a child from a permanent slot");
        }
        if (parent != null) {
//            internalHide();

            parent.children.remove(this);
            parent = null;
        }
        slot = null;
    }

    /**
     * Remove a child from its parent and make it a child of the current presenter.
     *
     * @param slot the new slot to add this {@code child} to.
     * @param child the child to adopt.
     */
    private <T extends VMVPPresenterComponent<?>> void adoptChild(IsSlot<T> slot, VMVPPresenterComponent<?> child) {
        if (child.parent != this) {
            if (child.parent != null) {
                if (!child.slot.isRemovable()) {
                    throw new IllegalArgumentException("Cannot move a child of a permanent slot to another slot");
                }
                child.parent.children.remove(child);
            }
            child.parent = this;
            children.add(child);
        }
        child.slot = slot;
    }
}

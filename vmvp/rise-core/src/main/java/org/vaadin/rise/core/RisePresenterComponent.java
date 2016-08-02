package org.vaadin.rise.core;

import com.vaadin.ui.Component;
import org.vaadin.rise.slot.*;
import org.vaadin.rise.slot.api.*;
import org.vaadin.rise.vaadin.IsComponent;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by oem on 7/12/16.
 */
public class RisePresenterComponent<VIEW extends RiseView> implements HasSlots, HasPopupsSlot, IsComponent, RisePresenter<VIEW> {

    private RisePresenterComponent<?> parent;
    private IsSlot<?> inSlot;
    protected boolean visible;

    private final VIEW view;

    private final Set<RisePresenterComponent<?>> children = new HashSet<>();

    protected PopupSlot<RisePresenterComponent<? extends PopupView<?>> > POPUP_SLOT = new PopupSlot<>();

    protected RisePresenterComponent(VIEW view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void addToPopupSlot(final RisePresenterComponent<? extends PopupView<?>> child) {
        addToSlot(POPUP_SLOT, child);
    }

    @Override
    public void removeFromPopupSlot(final RisePresenterComponent<? extends PopupView<?>> child) {
        removeFromSlot(POPUP_SLOT, child);
    }

    @Override
    public <T extends RisePresenterComponent<?>> void addToSlot(MultiSlot<T> slot, T child) {
        if (child == null) {
            throw new NullPointerException("Child cannot be null");
        }

        if (child.getInSlot() != slot && child.getParent() != this) {
            bindChild(slot, child);
            if (!child.isPopup()) {
                getView().addToSlot(slot, child);
            }
            if (isVisible()) {
                child.internalReveal();
            }
        }
    }

    @Override
    public void clearSlot(RemovableSlot<?> slot) {
        internalClearSlot(slot, null);
    }

    private void internalClearSlot(IsSlot<?> slot, RisePresenterComponent<?> dontRemove) {
        new HashSet<>(children)
                .stream()
                .filter(child -> child.getInSlot() == slot && !child.equals(dontRemove))
                .forEach(RisePresenterComponent::unBindChild);
    }

    @Override
    public <T extends RisePresenterComponent<?>> void removeFromSlot(RemovableSlot<T> slot, T child) {
        rawRemoveFromSlot(slot, child);
    }

    private void rawRemoveFromSlot(IsSlot<?> slot, RisePresenterComponent<?> child) {
        if (child == null || child.inSlot != slot) {
            return;
        }

        if (!child.isPopup()) {
            getView().removeFromSlot(slot, child);
        }

        child.unBindChild();
    }

    @Override
    public <T extends RisePresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child) {
        setInSlot(slot, child, true);
    }

    @Override
    public <T extends RisePresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child, boolean performReset) {
        if (child != null) {
            bindChild(slot, child);

            internalClearSlot(slot, child);

            if (!child.isPopup()) {
                getView().setInSlot(slot, child);
            }

            if (isVisible()) {
                child.internalReveal();
                if (performReset) {
//                    ResetPresentersEvent.fire(this);
                }
            }
        } else {
            clearSlot((RemovableSlot<?>) slot);
        }
    }

    @Override
    public <T extends RisePresenterComponent<?>> T getChild(IsSingleSlot<T> slot) {
        Iterator<T> it = getSlotChildren(slot).iterator();
        return it.hasNext() ? it.next() : null;
    }

    @Override
    public <T extends RisePresenterComponent<?>> Set<T> getChildren(Slot<T> slot) {
        return getSlotChildren(slot);
    }

    @Override
    public <T extends RisePresenterComponent<?> & Comparable<T>> List<T> getOrderedChildren(OrderedSlot<T> slot) {
        List<T> result = new ArrayList<>(getSlotChildren(slot));
        Collections.sort(result);
        return result;
    }

    boolean isPopup() {
        return inSlot != null && inSlot.isPopup();
    }


    IsSlot<?> getInSlot() {
        return inSlot;
    }

    RisePresenterComponent<?> getParent() {
        return parent;
    }

    void setInSlot(IsSlot<?> inSlot) {
        this.inSlot = inSlot;
    }

    protected void onReveal() {
    }

    protected void onHide() {
    }

    void internalReveal() {
        if (isVisible()) {
            return;
        }

        onReveal();
        visible = true;

        new HashSet<>(children)
            .forEach(RisePresenterComponent::internalReveal);

//        if (isPopup()) {
//            monitorCloseEvent((PresenterWidget<? extends PopupView>) this);
//            ((PopupView) getView()).showAndReposition();
//        }

//        registerVisibleHandlers();
    }

    void internalHide() {
        if (!isVisible()) {
            return;
        }
        children.forEach(RisePresenterComponent::internalHide);

//        if (isPopup()) {
//            ((PopupView) this.getView()).setCloseHandler(null);
//            ((PopupView) this.getView()).hide();
//        }
//
//        unregisterVisibleHandlers();

        visible = false;

        onHide();
    }

    private <T extends RisePresenterComponent<?>> void bindChild(IsSlot<T> slot, RisePresenterComponent<?> child) {
        if (child.parent != this) {
            if (child.parent != null) {
                if (!child.getInSlot().isRemovable()) {
                    throw new IllegalArgumentException("Cannot move a child of a permanent slot to another slot");
                }
                child.parent.children.remove(child);
            }
            child.parent = this;
            children.add(child);
        }
        child.setInSlot(slot);
    }

    private void unBindChild() {
        if (inSlot != null) {
            if (!inSlot.isRemovable()) {
                throw new IllegalArgumentException("Cannot remove a child from a permanent slot");
            }
            if (parent != null) {
                internalHide();

                parent.children.remove(this);
                parent = null;
            }
            inSlot = null;
        }
    }

    private <T extends RisePresenterComponent<?>> Set<T> getSlotChildren(IsSlot<T> slot) {
        return children
                .stream()
                .filter(child -> child.getInSlot() == slot)
                .map(child -> (T) child)
                .collect(Collectors.toSet());
    }

    public boolean isVisible() {
        return visible;
    }

    @Override
    public Component asComponent() {
        return getView().asComponent();
    }


    public VIEW getView() {
        return view;
    }
}

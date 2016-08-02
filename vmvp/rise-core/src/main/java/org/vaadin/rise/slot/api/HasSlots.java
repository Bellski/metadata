package org.vaadin.rise.slot.api;

import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.slot.MultiSlot;
import org.vaadin.rise.slot.OrderedSlot;
import org.vaadin.rise.slot.Slot;


import java.util.List;
import java.util.Set;

public interface HasSlots {

    <T extends RisePresenterComponent<?>> void addToSlot(MultiSlot<T> slot, T child);


    void clearSlot(RemovableSlot<?> slot);


    <T extends RisePresenterComponent<?>> void removeFromSlot(RemovableSlot<T> slot, T child);


    <T extends RisePresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child);


    <T extends RisePresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child, boolean performReset);


    <T extends RisePresenterComponent<?>> T getChild(IsSingleSlot<T> slot);


    <T extends RisePresenterComponent<?>> Set<T> getChildren(Slot<T> slot);

    <T extends RisePresenterComponent<?> & Comparable<T>> List<T> getOrderedChildren(OrderedSlot<T> slot);
}
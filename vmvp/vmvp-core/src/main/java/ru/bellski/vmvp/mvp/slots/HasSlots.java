package ru.bellski.vmvp.mvp.slots;

import ru.bellski.vmvp.mvp.VMVPPresenterComponent;

import java.util.List;
import java.util.Set;

public interface HasSlots {
    /**
     * This method adds some content in a specific slot of the {@link ru.bellski.vmvp.mvp.VMVPPresenterImpl}. The attached {@link ru.bellski.vmvp.mvp.VMVPView} should
     * manage this slot when its {@link View#addToSlot(Object, IsWidget)} is called.
     * <p/>
     * Contrary to the {@link #setInSlot} method, no {@link com.gwtplatform.mvp.client.proxy.ResetPresentersEvent
     * ResetPresentersEvent} is fired, so {@link VMVPPresenterComponent#onReset()} is not invoked.
     * <p/>
     * For more details on slots, see {@link HasSlots}.
     *
     * @param slot The slot into which the content is being added.
     * @param child The content, a {@link PresenterWidget}. Passing {@code null} will not add anything.
     */
    <T extends VMVPPresenterComponent<?>> void addToSlot(MultiSlot<T> slot, T child);

    /**
     * This method clears the content in a specific slot. No
     * {@link com.gwtplatform.mvp.client.proxy.ResetPresentersEvent ResetPresentersEvent} is fired. The attached
     * {@link View} should manage this slot when its {@link View#setInSlot(Object, IsWidget)} is called. It should also
     * clear the slot when the {@link View#setInSlot(Object, IsWidget)} method is called with {@code null} as a
     * parameter.
     * <p/>
     * For more details on slots, see {@link HasSlots}.
     *
     * @param slot The slot to clear.
     */
    void clearSlot(RemovableSlot<?> slot);

    /**
     * This method removes some content in a specific slot of the {@link Presenter}. No {@link
     * com.gwtplatform.mvp.client.proxy.ResetPresentersEvent ResetPresentersEvent} is fired. The attached {@link View}
     * should manage this slot when its {@link View#removeFromSlot(Object, IsWidget)} is called.
     * <p/>
     * For more details on slots, see {@link HasSlots}.
     *
     * @param slot The slot for which the content is being removed.
     * @param child The content, a {@link PresenterWidget}. Passing {@code null} will not remove anything.
     */
    <T extends VMVPPresenterComponent<?>> void removeFromSlot(RemovableSlot<T> slot, T child);

    /**
     * This method sets some content in a specific slot of the {@link Presenter}. A {@link
     * com.gwtplatform.mvp.client.proxy.ResetPresentersEvent ResetPresentersEvent} will be fired after the top-most
     * visible presenter is revealed, resulting in a call to {@link PresenterWidget#onReset()}.
     * <p/>
     * For more details on slots, see {@link HasSlots}.
     *
     * @param slot The slot for which the content is being set. The attached view should know what to do with this
     * slot.
     * @param child The content, a {@link PresenterWidget}. Passing {@code null} will clear the slot.
     */
    <T extends VMVPPresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child);

    /**
     * This method sets some content in a specific slot of the {@link Presenter}. The attached {@link View} should
     * manage this slot when its {@link View#setInSlot(Object, IsWidget)} is called. It should also clear the slot when
     * the {@code setInSlot} method is called with {@code null} as a parameter.
     * <p/>
     * For more details on slots, see {@link HasSlots}.
     *
     * @param slot The slot for which the content is being set.
     * @param child The content, a {@link PresenterWidget}. Passing {@code null} will clear the slot.
     * @param performReset Pass {@code true} if you want a {@link com.gwtplatform.mvp.client.proxy.ResetPresentersEvent
     * ResetPresentersEvent} to be fired after the content has been added and this presenter is visible, pass {@code
     * false} otherwise.
     */
    <T extends VMVPPresenterComponent<?>> void setInSlot(IsSlot<T> slot, T child, boolean performReset);

    /**
     * Get the child of SingleSlot.
     *
     * @param slot - the slot
     *
     * @return the child of the slot or null if the slot is empty.
     */
    <T extends VMVPPresenterComponent<?>> T getChild(IsSingleSlot<T> slot);

    /**
     * Get the children of a slot.
     *
     * @param slot - the slot
     *
     * @return the children of the slot.
     */
    <T extends VMVPPresenterComponent<?>> Set<T> getChildren(Slot<T> slot);

    /**
     * Get the children of an ordered slot.
     *
     * @param slot - an ordered slot
     *
     * @return the children of the slot in a sorted list.
     */
    <T extends VMVPPresenterComponent<?> & Comparable<T>> List<T> getChildren(OrderedSlot<T> slot);
}
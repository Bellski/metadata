package org.vaadin.rise.slot.api;

import org.vaadin.rise.core.RisePresenterComponent;

public interface IsSingleSlot<T extends RisePresenterComponent<?>> extends IsSlot<T> {

    <PRESENTER_COMPONENT extends RisePresenterComponent<?>> void setContent(PRESENTER_COMPONENT content);
}
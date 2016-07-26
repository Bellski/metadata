package org.vaadin.rise.deprecated.proxy.slot;

import org.vaadin.rise.core.RisePresenterComponent;

public interface IsSingleSlot<T extends RisePresenterComponent<?>> extends IsSlot<T> {

    <PRESENTER_COMPONENT extends RisePresenterComponent<?>> void setContent(PRESENTER_COMPONENT content);
}
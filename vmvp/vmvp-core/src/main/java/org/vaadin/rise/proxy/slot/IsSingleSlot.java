package org.vaadin.rise.proxy.slot;

import org.vaadin.rise.core.RisePresenterComponent;

public interface IsSingleSlot<T extends RisePresenterComponent<?>> extends IsSlot<T> {
	<T extends RisePresenterComponent<?>> void setContent(T content);
}
package org.vaadin.rise.deprecated.proxy;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.deprecated.Place;

public interface ProxyPlace<P extends RisePresenterImpl<?>> extends Proxy<P>, Place {

    void manualReveal(RisePresenterImpl<?> presenter);

    void manualRevealFailed();
}
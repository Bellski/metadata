package org.vaadin.rise.proxy;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.Place;

public interface ProxyPlace<P extends RisePresenterImpl<?>> extends Proxy<P>, Place {

    void manualReveal(RisePresenterImpl<?> presenter);

    void manualRevealFailed();
}
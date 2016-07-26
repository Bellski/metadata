package org.vaadin.rise.deprecated.proxy;

import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public interface Proxy<PRESENTER extends RisePresenterImpl<?>> {
    PRESENTER getPresenter();

}

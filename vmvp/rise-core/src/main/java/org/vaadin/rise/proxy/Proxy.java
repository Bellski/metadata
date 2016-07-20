package org.vaadin.rise.proxy;

import com.google.web.bindery.event.shared.EventBus;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.HasHandlers;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.proxy.slot.IsSlot;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public interface Proxy<PRESENTER extends RisePresenterImpl<?>> {
    PRESENTER getPresenter();

}

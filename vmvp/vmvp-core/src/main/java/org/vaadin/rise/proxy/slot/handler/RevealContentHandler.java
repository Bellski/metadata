package org.vaadin.rise.proxy.slot.handler;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import org.vaadin.rise.core.event.RiseEventHandler;
import org.vaadin.rise.proxy.slot.event.RevealContentEvent;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public interface RevealContentHandler extends RiseEventHandler {
    @Subscribe
    void onRevealContent(final RevealContentEvent event);
}

package org.vaadin.rise.test.application.proxy;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;
import org.vaadin.rise.test.application.proxy.events.RevealContentEvent;

public class RevealContentHandler<T extends VMVPPresenterImpl<?, ?>> {
    private final VMVPEventBus eventBus;
    private final ProxyImpl<T> proxy;

    public RevealContentHandler(
            VMVPEventBus eventBus,
            ProxyImpl<T> proxy) {
        this.eventBus = eventBus;
        this.proxy = proxy;
    }

    /**
     * This is the dispatched method.
     *
     * @param event The event containing the presenter that wants to bet set as content.
     */
    @Subscribe
    public final void onRevealContent(final RevealContentEvent event) {

    }
}
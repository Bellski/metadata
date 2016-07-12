package org.vaadin.rise.test.application.proxy;

import dagger.Lazy;
import org.vaadin.rise.test.application.proxy.events.VMVPEvent;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;


/**
 * Created by Aleksandr on 10.07.2016.
 */
public class ProxyImpl<PRESENTER_IMPL extends VMVPPresenterImpl<?, ?>> implements Proxy<PRESENTER_IMPL> {
    private final Lazy<PRESENTER_IMPL> lazyPresenter;
    private final VMVPEventBus vmvpEventBus;

    public ProxyImpl(VMVPEventBus vmvpEventBus, Lazy<PRESENTER_IMPL> lazyPresenter) {
        this.lazyPresenter = lazyPresenter;
        this.vmvpEventBus = vmvpEventBus;
    }

    @Override
    public PRESENTER_IMPL getPresenter() {
        return lazyPresenter.get();
    }

    protected void fireEvent(VMVPEvent event) {
        vmvpEventBus.post(event);
    }
}

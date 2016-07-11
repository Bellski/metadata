package ru.bellski.vmvp.proxy;

import dagger.Lazy;
import ru.bellski.vmvp.mvp.VMVPPresenterImpl;
import ru.bellski.vmvp.proxy.events.VMVPEvent;


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

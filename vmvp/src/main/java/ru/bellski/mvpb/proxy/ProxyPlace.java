package ru.bellski.mvpb.proxy;

import ru.bellski.mvpb.mvp.VMVPPresenterImpl;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface ProxyPlace<PRESENTER_IMPL extends VMVPPresenterImpl<?,?>> extends Proxy<PRESENTER_IMPL>, Place {
}

package org.vaadin.rise.test.application.proxy;


import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface ProxyPlace<PRESENTER_IMPL extends VMVPPresenterImpl<?,?>> extends Proxy<PRESENTER_IMPL>, Place {
}

package org.vaadin.rise.test.application.proxy;


import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface Proxy<PRESENTER_IMPL extends VMVPPresenterImpl<?,?>> {
    PRESENTER_IMPL getPresenter();
}

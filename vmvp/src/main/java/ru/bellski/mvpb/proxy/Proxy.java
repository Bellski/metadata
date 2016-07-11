package ru.bellski.mvpb.proxy;

import ru.bellski.mvpb.mvp.VMVPPresenterImpl;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface Proxy<PRESENTER_IMPL extends VMVPPresenterImpl<?,?>> {
    PRESENTER_IMPL getPresenter();
}

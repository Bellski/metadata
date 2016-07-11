package ru.bellski.mvpb.mvp;

import ru.bellski.mvpb.proxy.Proxy;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class VMVPPresenterImpl<VIEW extends VMVPView, PROXY extends Proxy<?>> extends VMVPPresenterComponent<VIEW> implements VMVPPresenter<VIEW> {

    public VMVPPresenterImpl(VIEW view, PROXY proxy) {
        super(view);
    }
}

package ru.bellski.mvpb.mvp;

import com.vaadin.ui.Component;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 09.07.2016.
 */
public class VMVPViewImpl<PRESENTER extends VMVPPresenter> implements VMVPView<PRESENTER> {

    @Inject
    public VMVPViewImpl() {
    }

    @Override
    public Component asComponent() {
        return null;
    }
}

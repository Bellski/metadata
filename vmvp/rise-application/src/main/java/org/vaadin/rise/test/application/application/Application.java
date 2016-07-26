package org.vaadin.rise.test.application.application;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public interface Application {
    interface View extends RiseView<Presenter> {

    }

    interface Presenter extends RisePresenter<View> {

    }
}
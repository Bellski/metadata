package org.vaadin.rise.test.application.application.error;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public interface Error {
    interface View extends RiseView<Presenter> {

    }

    interface Presenter extends RisePresenter<View> {

    }
}

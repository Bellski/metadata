package org.vaadin.rise.test.application.plugins.someplugin;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public interface SomePlugin {
    interface View extends RiseView<Presenter> {

    }

    interface Presenter extends RisePresenter<View> {

    }
}

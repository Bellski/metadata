package org.vaadin.rise.test.application.application.error;

import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.test.application.application.Cas1Entry;

/**
 * Created by Aleksandr on 12.07.2016.
 */

@RiseModule(
        view = ErrorView.class,
		viewApi = Error.View.class,
        presenter = ErrorPresenter.class,
		presenterApi = Error.Presenter.class,
        parent = Cas1Entry.class
)
public class ErrorModule {
}

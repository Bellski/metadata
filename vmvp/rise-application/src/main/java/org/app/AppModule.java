package org.app;

import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.core.annotation.RiseModule;

/**
 * Created by oem on 7/29/16.
 */
@ApplicationEntry
@RiseModule(
	viewApi = App.View.class,
	view = AppView.class,
	presenterApi = App.Presenter.class,
	presenter = AppPresenter.class
)
public class AppModule {
}

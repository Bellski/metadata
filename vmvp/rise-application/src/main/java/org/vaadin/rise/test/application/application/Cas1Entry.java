package org.vaadin.rise.test.application.application;

import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.core.annotation.RiseModule;

/**
 * Created by oem on 7/12/16.
 */
@ApplicationEntry
@RiseModule(
	view = Cas1View.class,
	viewApi = Cas1.View.class,
	presenter = Cas1Presenter.class,
	presenterApi = Cas1.Presenter.class
)
public class Cas1Entry {
}

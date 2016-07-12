package org.vaadin.rise.test.application.application;

import org.vaadin.rise.test.application.proxy.PlaceManager;
import org.vaadin.rise.test.application.mvp.root.RootPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
@Singleton
public class Bootstrap {

	@Inject
	public Bootstrap(PlaceManager placeManager, RootPresenter presenter) {
		placeManager.revealCurrentPlace();
	}
}

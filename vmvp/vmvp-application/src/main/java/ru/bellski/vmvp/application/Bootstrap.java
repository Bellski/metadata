package ru.bellski.vmvp.application;

import ru.bellski.vmvp.mvp.root.RootPresenter;
import ru.bellski.vmvp.proxy.PlaceManager;

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

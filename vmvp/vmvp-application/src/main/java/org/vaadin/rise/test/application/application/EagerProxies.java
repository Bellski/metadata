package org.vaadin.rise.test.application.application;

import org.vaadin.rise.test.application.application.home.HomePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
@Singleton
public class EagerProxies {

	@Inject
	public EagerProxies(ApplicationPresenter.ApplicationPresenterProxy applicationPresenterProxy,
						HomePresenter.HomePresenterProxy homePresenterProxy) {

	}
}

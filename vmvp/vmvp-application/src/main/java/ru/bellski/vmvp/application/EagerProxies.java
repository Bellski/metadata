package ru.bellski.vmvp.application;

import ru.bellski.vmvp.application.home.HomePresenter;

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

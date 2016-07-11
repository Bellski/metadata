package ru.bellski.vmvp.application.home;

import dagger.Lazy;
import ru.bellski.vmvp.application.ApplicationPresenter;
import ru.bellski.vmvp.mvp.VMVPPresenterImpl;
import ru.bellski.vmvp.proxy.ProxyPlaceImpl;
import ru.bellski.vmvp.proxy.VMVPEventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
public class HomePresenter extends VMVPPresenterImpl<Home.View, HomePresenter.HomePresenterProxy> implements Home.Presenter {


	@Singleton
	public static class HomePresenterProxy extends ProxyPlaceImpl<HomePresenter> {

		@Inject
		public HomePresenterProxy(VMVPEventBus vmvpEventBus, Lazy<HomePresenter> lazyPresenter) {
			super(vmvpEventBus, lazyPresenter, "home");
		}
	}

	@Inject
	public HomePresenter(VMVPEventBus eventBus, Home.View view, HomePresenterProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN_CONTENT);
		System.out.println("HomePresenter ");
	}

}

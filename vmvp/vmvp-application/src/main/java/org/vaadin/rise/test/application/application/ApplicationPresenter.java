package org.vaadin.rise.test.application.application;

import dagger.Lazy;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;
import org.vaadin.rise.test.application.mvp.slots.NestedSlot;
import org.vaadin.rise.test.application.proxy.ProxyImpl;
import org.vaadin.rise.test.application.proxy.VMVPEventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
public class ApplicationPresenter extends VMVPPresenterImpl<Application.View, ApplicationPresenter.ApplicationPresenterProxy> implements Application.Presenter {

	@Singleton
	public static class SLOT_MAIN_CONTENT extends NestedSlot {
		@Inject
		public SLOT_MAIN_CONTENT() {
		}
	}


	@Singleton
	public static class ApplicationPresenterProxy extends ProxyImpl<ApplicationPresenter> {

		@Inject
		public ApplicationPresenterProxy(VMVPEventBus vmvpEventBus, Lazy<ApplicationPresenter> lazyPresenter) {
			super(vmvpEventBus, lazyPresenter);
		}
	}

	public static final NestedSlot SLOT_MAIN_CONTENT = new NestedSlot();

	@Inject
	public ApplicationPresenter(VMVPEventBus eventBus, Application.View view, ApplicationPresenterProxy applicationPresenterProxy) {
		super(eventBus, view, applicationPresenterProxy, RevealType.Root);
		System.out.println("ApplicationPresenter");
	}
}

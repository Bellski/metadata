package org.vaadin.rise.test.application.application;

import dagger.Component;
import org.vaadin.rise.test.application.mvp.VMVPPresenter;
import org.vaadin.rise.test.application.mvp.VMVPView;

import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */

@Singleton
@Component(modules = ApplicationBootstrapModule.class)
public interface Application {
	interface View extends VMVPView<Presenter> {

	}

	interface Presenter extends VMVPPresenter<View> {

	}

	ApplicationPresenter applicationPresenter();
	EagerProxies instantinateProxies();
	Bootstrap bootstrap();
}

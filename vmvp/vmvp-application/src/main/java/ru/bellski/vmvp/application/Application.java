package ru.bellski.vmvp.application;

import dagger.Component;
import ru.bellski.vmvp.mvp.VMVPPresenter;
import ru.bellski.vmvp.mvp.VMVPView;

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

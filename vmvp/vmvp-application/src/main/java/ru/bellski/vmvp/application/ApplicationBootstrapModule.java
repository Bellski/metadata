package ru.bellski.vmvp.application;

import dagger.Module;
import dagger.Provides;
import ru.bellski.vmvp.annotations.DefaultPlace;
import ru.bellski.vmvp.annotations.ErrorPlace;
import ru.bellski.vmvp.annotations.UnauthorizedPlace;
import ru.bellski.vmvp.application.home.HomeModule;
import ru.bellski.vmvp.mvp.VMVPModule;
import ru.bellski.vmvp.mvp.root.RootModule;

/**
 * Created by oem on 7/11/16.
 */
@Module(includes = {HomeModule.class, RootModule.class})
public class ApplicationBootstrapModule extends VMVPModule<Application.View, ApplicationView, Application.Presenter, ApplicationPresenter> {

	@Provides
	@DefaultPlace
	String providesDefaultPlace() {
		return "!home";
	}

	@Provides
	@ErrorPlace
	String providesErrorPlace() {
		return "!home";
	}

	@Provides
	@UnauthorizedPlace
	String providesUnauthorizedPlace() {
		return "!home";
	}
}


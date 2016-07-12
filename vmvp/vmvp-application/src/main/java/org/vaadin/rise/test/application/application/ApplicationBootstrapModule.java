package org.vaadin.rise.test.application.application;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.test.application.annotations.DefaultPlace;
import org.vaadin.rise.test.application.annotations.UnauthorizedPlace;
import org.vaadin.rise.test.application.mvp.root.RootModule;
import org.vaadin.rise.test.application.annotations.ErrorPlace;
import org.vaadin.rise.test.application.application.home.HomeModule;
import org.vaadin.rise.test.application.mvp.VMVPModule;

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


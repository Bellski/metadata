package org.vaadin.rise.test.application.mvp.root;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.test.application.proxy.PlaceManager;
import org.vaadin.rise.test.application.VaadinModule;
import org.vaadin.rise.test.application.mvp.VMVPModule;
import org.vaadin.rise.test.application.navigation.token.ParameterTokenFormatter;
import org.vaadin.rise.test.application.navigation.token.PlaceTokenRegistry;
import org.vaadin.rise.test.application.navigation.token.TokenFormatter;
import org.vaadin.rise.test.application.proxy.DefaultPlaceManager;
import org.vaadin.rise.test.application.proxy.VMVPEventBus;

import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
@Module(includes = VaadinModule.class)
public class RootModule extends VMVPModule<Root.View, RootView, Root.Presenter, RootPresenter> {

	@Singleton
	@Provides
	public TokenFormatter providesParameterTokenFormatter() {
		return new ParameterTokenFormatter();
	}

	@Singleton @Provides
	public PlaceTokenRegistry providesPlaceTokenRegistry() {
		return new PlaceTokenRegistry();
	}

	@Singleton @Provides
	public VMVPEventBus providesEventBus() {
		return new VMVPEventBus();
	}

	@Singleton @Provides
	public PlaceManager providesPlaceManager(DefaultPlaceManager placeManager){
		return placeManager;
	}
}

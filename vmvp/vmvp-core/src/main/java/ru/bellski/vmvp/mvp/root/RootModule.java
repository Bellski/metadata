package ru.bellski.vmvp.mvp.root;

import dagger.Module;
import dagger.Provides;
import ru.bellski.vmvp.VaadinModule;
import ru.bellski.vmvp.mvp.VMVPModule;
import ru.bellski.vmvp.navigation.token.ParameterTokenFormatter;
import ru.bellski.vmvp.navigation.token.PlaceTokenRegistry;
import ru.bellski.vmvp.navigation.token.TokenFormatter;
import ru.bellski.vmvp.proxy.DefaultPlaceManager;
import ru.bellski.vmvp.proxy.PlaceManager;
import ru.bellski.vmvp.proxy.VMVPEventBus;

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

package org.vaadin.rise.place;

import com.vaadin.server.Page;
import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.place.token.PlaceTokenRegistry;
import org.vaadin.rise.place.token.RouteTokenFormatter;
import org.vaadin.rise.place.token.TokenFormatter;

import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Module
public class PlaceManagerModule {

	@Provides @Singleton
	DefaultPlaceManager providesPlaceManager(TokenFormatter tokenFormatter, Page page) {
		return new DefaultPlaceManager(tokenFormatter, page, "", "", "");
	}


	@Provides @Singleton
	TokenFormatter providesTokenFormatter(PlaceTokenRegistry tokenRegistry) {
		return new RouteTokenFormatter(tokenRegistry);
	}
}

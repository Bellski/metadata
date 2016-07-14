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
	private final String defaultPlace;
	private final String errorPlace;
	private final String unauthorizedPlace;

	public PlaceManagerModule(String defaultPlace, String errorPlace, String unauthorizedPlace) {

		this.defaultPlace = defaultPlace;
		this.errorPlace = errorPlace;
		this.unauthorizedPlace = unauthorizedPlace;
	}

	@Provides @Singleton
	DefaultPlaceManager providesPlaceManager(TokenFormatter tokenFormatter, Page page) {
		return new DefaultPlaceManager(tokenFormatter, page, defaultPlace, errorPlace, unauthorizedPlace);
	}


	@Provides @Singleton
	TokenFormatter providesTokenFormatter(PlaceTokenRegistry tokenRegistry) {
		return new RouteTokenFormatter(tokenRegistry);
	}
}

package org.vaadin.rise.place;

import com.vaadin.server.Page;
import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.token.PlaceTokenRegistry;
import org.vaadin.rise.place.token.RouteTokenFormatter;


import javax.inject.Singleton;


@Module
public class PlaceManagerModule {

	@Provides @Singleton
	DefaultPlaceManager providesPlaceManager(RouteTokenFormatter tokenFormatter, Page page) {
		return new DefaultPlaceManager(tokenFormatter, page, "sub", "sub", "sub", "!/");
	}


	@Provides @Singleton
	RouteTokenFormatter providesTokenFormatter(PlaceTokenRegistry tokenRegistry) {
		return new RouteTokenFormatter(tokenRegistry);
	}
}

package org.vaadin.rise.place;

import com.vaadin.server.Page;
import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.deprecated.DefaultPlaceManager_TODO_TODO;
import org.vaadin.rise.place.deprecated.token.PlaceTokenRegistry;
import org.vaadin.rise.place.deprecated.token.RouteTokenFormatter;


import javax.inject.Singleton;


@Module
public class PlaceManagerModule {

	@Provides @Singleton
	DefaultPlaceManager_TODO_TODO providesPlaceManager(RouteTokenFormatter tokenFormatter, Page page) {
		return new DefaultPlaceManager_TODO_TODO(tokenFormatter, page, "sub", "sub", "sub", "!/");
	}


	@Provides @Singleton
	RouteTokenFormatter providesTokenFormatter(PlaceTokenRegistry tokenRegistry) {
		return new RouteTokenFormatter(tokenRegistry);
	}
}

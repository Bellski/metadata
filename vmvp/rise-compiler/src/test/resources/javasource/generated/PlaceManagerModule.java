package org.vaadin.rise.place;

import com.vaadin.server.Page;
import dagger.Module;
import dagger.Provides;
import javasource.EntryPlaceBus;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;

import javax.inject.Singleton;
import java.util.Map;


@Module
public class PlaceManagerModule {

	@Provides @Singleton
	PlaceManager providesPlaceManager(@Places Map<String, Place> placeMap,
									  UriFragmentSource uriFragmentSource,
									  EntryPlaceBus bus) {
		return new DefaultPlaceManager(
			placeMap,
			ApplicationNameTokens.nameTokens,
			bus,
			uriFragmentSource,
			"sub",
			"sub",
			"sub"
		);
	}

	@Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
		return pageUriFragmentSource;
	}
}

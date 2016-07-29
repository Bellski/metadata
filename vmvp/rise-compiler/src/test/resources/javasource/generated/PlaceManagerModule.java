package javasource;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PageUriFragmentSource;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;

import javax.inject.Singleton;
import java.util.Map;


import javasource.NameTokenParts;


@Module
public class PlaceManagerModule {

	@Provides
	@Singleton
	PlaceManager providesPlaceManager(@Places Map<String, Place> placeMap, UriFragmentSource uriFragmentSource, DefaultEntryGatekeeper gateKeeper) {
		return new DefaultPlaceManager(
			placeMap,
			NameTokenParts.parts,
			uriFragmentSource,
			gateKeeper,
			"!/sub",
			"!/sub",
			"!/subsub"
		);
	}

	@Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
		return pageUriFragmentSource;
	}
}

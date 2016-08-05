package javasource;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.error.BaseErrorManager;
import org.vaadin.rise.error.ErrorManager;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PageUriFragmentSource;
import org.vaadin.rise.place.annotation.DefaultPlace;
import org.vaadin.rise.place.annotation.ErrorPlace;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.annotation.UnauthorizedPlace;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;

import javax.inject.Singleton;
import java.util.Map;



@Module
public class PlaceManagerModule {

	@Provides
	@Singleton
	PlaceManager providesPlaceManager(@Places Map<String, Place> placeMap, UriFragmentSource uriFragmentSource, DefaultEntryGatekeeper gateKeeper, ErrorManager errorManager) {
		return new DefaultPlaceManager(
			placeMap,
			NameTokenParts.parts,
			uriFragmentSource,
			gateKeeper,
			"!/sub",
			"!/subsub",
			errorManager
		);
	}

	@Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
		return pageUriFragmentSource;
	}

	@Provides @Singleton static ErrorManager errorManager(BaseErrorManager baseErrorManager) {
		return baseErrorManager;
	}

	@Provides @DefaultPlace @Singleton static String defaultPlace() {
		return "!/sub";
	}

	@Provides @ErrorPlace @Singleton static String errorPlace() {
		return "!/sub";
	}

	@Provides @UnauthorizedPlace @Singleton static String unauthorizedPlace() {
		return "!/subsub";
	}
}

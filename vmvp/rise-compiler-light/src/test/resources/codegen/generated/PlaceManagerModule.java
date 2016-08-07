package codegen.presenters;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.error.ErrorManager;
import java.util.Map;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.annotation.DefaultPlace;
import org.vaadin.rise.place.annotation.ErrorPlace;
import org.vaadin.rise.place.annotation.UnauthorizedPlace;
import org.vaadin.rise.place.api.UriFragmentSource;
import org.vaadin.rise.place.PageUriFragmentSource;

import org.vaadin.rise.error.BaseErrorManager;

import javax.inject.Singleton;


@Module
public class PlaceManagerModule {


	@Provides @Singleton
	PlaceManager providesPlaceManager(@Places Map<String, Place> placeMap,
									  UriFragmentSource uriFragmentSource,
									  ErrorManager errorManager,
									  @DefaultPlace String defaultPlace,
									  @UnauthorizedPlace String unauthorizedPlace) {
		return new DefaultPlaceManager(
			placeMap,
			NameTokenParts.parts,
			uriFragmentSource,
			defaultPlace,
			unauthorizedPlace,
			errorManager
		);
	}

	@Provides
	@Singleton
	static ErrorManager errorManager(BaseErrorManager errorManager) {
		return errorManager;
	}

	@Provides
	@Singleton
	static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
		return pageUriFragmentSource;
	}

	@Provides
	@DefaultPlace
	@Singleton
	static String defaultPlace() {
		return "!/user";
	}

	@Provides
	@ErrorPlace
	@Singleton
	static String errorPlace() {
		return "!/user";
	}

	@Provides
	@UnauthorizedPlace
	@Singleton
	static String unauthorizedPlace() {
		return "!/user";
	}
}
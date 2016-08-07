package codegen.presenters;


import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import dagger.Lazy;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;

import javax.inject.Singleton;

@Module
public class PlacesModule {

	@Provides
	@Singleton
	@IntoMap
	@Places
	@StringKey("!/home")
	Place homePlaceValue(Lazy<HomePresenter> lazyPlacePresenter) {
		return new PresenterPlace<>(
			lazyPlacePresenter,
			"!/home",
			"!/home",
			null,
			null
		);
	}

	@Provides
	@Singleton
	@IntoMap
	@Places
	@StringKey("!/user")
	Place userPlaceValue(Lazy<UserPresenter> lazyPlacePresenter) {
		return new PresenterPlace<>(
			lazyPlacePresenter,
			"!/user",
			"!/user",
			null,
			null
		);
	}
}
package codegen.presenters;


import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.vaadin.rise.place.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;

import codegen.presenters.HomePresenter;
import codegen.presenters.UserPresenter;

import javax.inject.Singleton;

@Module
public class PlacesModule {

	@Provides
	@Singleton
	@IntoMap
	@Places
	@StringKey("!/home")
	Place homePlaceValue(LazyPlacePresenter<HomePresenter> lazyPlacePresenter) {
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
	Place userPlaceValue(LazyPlacePresenter<UserPresenter> lazyPlacePresenter) {
		return new PresenterPlace<>(
			lazyPlacePresenter,
			"!/user",
			"!/user",
			null,
			null
		);
	}
}
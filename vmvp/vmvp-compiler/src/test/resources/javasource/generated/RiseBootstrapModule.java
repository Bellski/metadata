package javasource;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.core.Root;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.RootView;
import org.vaadin.rise.place.token.PlaceTokenRegistry;
import org.vaadin.rise.vaadin.VaadinModule;

import org.vaadin.rise.place.PlaceManagerModule;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;


@Module(includes = {VaadinModule.class, PlaceManagerModule.class})
public class RiseBootstrapModule {

	@Provides @Singleton
	PlaceTokenRegistry providesPlaceTokenRegistry() {
		return new PlaceTokenRegistry() {
			@Override
			public Set<String> getAllPlaceTokens() {
				final Set<String> places = new HashSet<>();
				places.add("sub");
				places.add("subsub");
				return places;
			}
		};

	}

	@Provides @Singleton
	RootPresenter.RootSlot providesRootSlot(RootPresenter.RiseRootSlot rootSlot) {
		return rootSlot;
	}

	@Provides @Singleton
	Root.Presenter providesRootPresenter(RootPresenter rootPresenter) {
		return rootPresenter;
	}

	@Provides @Singleton
	Root.View providesRootView(RootView rootView) {
		return rootView;
	}
}

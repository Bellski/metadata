package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.deprecated.proxy.Proxy;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;

@Module
public class RiseSubEntryModule extends SubEntryModule {

    @Provides
    @Singleton
    @IntoMap
    @Places
    @StringKey("!/sub")
    Place placeValue(LazyPlacePresenter<SubEntryPresenter> lazyPlacePresenter) {
        return new PresenterPlace<>(
            lazyPlacePresenter,
            "!/sub",
            "!/sub",
            null,
            null
        );
    }

    @Provides @Singleton
    SubEntry.View providesSubEntryView(SubEntryView view) {
        return view;
    }

    @Provides @Singleton
    SubEntry.Presenter providesSubEntryPresenter(SubEntryPresenter presenter) {
        return presenter;
    }
}
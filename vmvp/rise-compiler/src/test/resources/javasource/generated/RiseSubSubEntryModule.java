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
public class RiseSubSubEntryModule extends SubSubEntryModule {


    @Provides
    @Singleton
    @IntoMap
    @Places
    @StringKey("!/subsub")
    Place placeValue(LazyPlacePresenter<SubSubEntryPresenter> lazyPlacePresenter) {
        return new PresenterPlace<>(
            lazyPlacePresenter,
            "!/subsub",
            "!/subsub",
            null,
            null
        );
    }


    @Provides @Singleton
    SubSubEntry.View providesSubSubEntryView(SubSubEntryView view) {
        return view;
    }

    @Provides @Singleton
    SubSubEntry.Presenter providesSubSubEntryPresenter(SubSubEntryPresenter presenter) {
        return presenter;
    }
}
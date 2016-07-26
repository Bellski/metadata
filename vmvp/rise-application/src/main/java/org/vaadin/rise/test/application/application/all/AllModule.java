package org.vaadin.rise.test.application.application.all;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.vaadin.rise.core.dagger.RootModule;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.dagger.PlaceManagerModule;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module
public class AllModule {

    @Provides @IntoSet @Singleton String startUriPlace() {
        return "!";
    }

    @Provides @IntoSet @Singleton String pluginsPlace() {
        return "plugins";
    }

    @Provides @Singleton @IntoSet Map.Entry<Place, Place> place(LazyPlacePresenter<AllPresenter> lazyPlacePresenter) {
        final PresenterPlace<AllPresenter> place = new PresenterPlace<>(
                lazyPlacePresenter,
                "!/plugins/{name}",
                new String[]{"!", "plugins"},
                new String[]{"!", "plugins"},
                new int[]{0, 1},
                new String[] {"name"},
                new int[] {2}
        );

        return new AbstractMap.SimpleImmutableEntry<>(place, place);
    }

    @Provides @Singleton All.Presenter presenter(AllPresenter presenter) {
        return presenter;
    }

    @Provides @Singleton All.View view(AllView view) {
        return view;
    }
}

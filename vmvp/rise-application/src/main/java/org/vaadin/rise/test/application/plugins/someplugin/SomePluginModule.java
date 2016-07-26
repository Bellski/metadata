package org.vaadin.rise.test.application.plugins.someplugin;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.BusPlace;
import org.vaadin.rise.place.annotation.BusPlaces;
import org.vaadin.rise.place.api.Place;

import javax.inject.Singleton;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module
public class SomePluginModule {

    @Provides @IntoSet @Singleton String startUriPlace() {
        return "!";
    }

    @Provides @IntoSet @Singleton String pluginsPlace() {
        return "plugins";
    }

    @Provides @IntoSet @Singleton String somePluginPlace() {
        return "somePlugin";
    }

    @Provides @Singleton @IntoSet @BusPlace Map.Entry<Place, Place> place(LazyPlacePresenter<SomePluginPresenter> lazyPlacePresenter) {
        final PresenterPlace<SomePluginPresenter> place = new PresenterPlace<>(
                lazyPlacePresenter,
                "!/plugins/somePlugin",
                new String[]{"!", "plugins", "somePlugin"},
                new String[]{"!", "plugins", "somePlugin"},
                new int[]{0, 1, 2},
                null,
                null
        );

        return new AbstractMap.SimpleImmutableEntry<>(place, place);
    }

    @Provides @Singleton SomePlugin.Presenter presenter(SomePluginPresenter presenter) {
        return presenter;
    }

    @Provides @Singleton SomePlugin.View view(SomePluginView view) {
        return view;
    }
}

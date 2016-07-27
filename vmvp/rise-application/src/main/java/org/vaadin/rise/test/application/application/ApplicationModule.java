package org.vaadin.rise.test.application.application;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.vaadin.rise.core.dagger.RootModule;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.NameTokens;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.test.application.PlaceManagerModule;
import org.vaadin.rise.test.application.application.plugin.PluginModule;
import org.vaadin.rise.test.application.plugins.PluginBusModule;
import org.vaadin.rise.vaadin.VaadinModule;

import javax.inject.Singleton;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module(includes = {VaadinModule.class, PlaceManagerModule.class, RootModule.class, PluginModule.class, PluginBusModule.class})
public class ApplicationModule {

    @Provides @Singleton @NameTokens Map<String, String> nameTokenMap() {
        final Map<String, String> nameTokenMap = new HashMap<>();
        nameTokenMap.put("!", "!");
        nameTokenMap.put("plugins", "plugins");
        nameTokenMap.put("all", "all");

        return nameTokenMap;
    }

    @Provides @Singleton @IntoSet Map.Entry<Place, Place> place(LazyPlacePresenter<ApplicationPresenter> lazyPlacePresenter) {
        final PresenterPlace<ApplicationPresenter> place = new PresenterPlace<>(
                lazyPlacePresenter,
                "!/plugins",
                new String[]{"!", "plugins"},
                new String[]{"!", "plugins"},
                new int[]{0, 1},
                null,
                null
        );

        return new AbstractMap.SimpleImmutableEntry<>(place, place);
    }

    @Provides @Singleton Application.Presenter presenter(ApplicationPresenter presenter) {
        return presenter;
    }

    @Provides @Singleton Application.View view(ApplicationView view) {
        return view;
    }
}

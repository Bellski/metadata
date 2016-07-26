package org.vaadin.rise.test.application.application.plugin;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.api.Place;

import javax.inject.Singleton;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module
public class PluginModule {

//    @Provides @IntoSet @Singleton String startUriPlace() {
//        return "!";
//    }
//
//    @Provides @IntoSet @Singleton String pluginsPlace() {
//        return "plugins";
//    }
//
//    @Provides @Singleton @IntoSet Map.Entry<Place, Place> place(LazyPlacePresenter<PluginPresenter> lazyPlacePresenter) {
//        final PresenterPlace<PluginPresenter> place = new PresenterPlace<>(
//                lazyPlacePresenter,
//                "!/plugins/{name}",
//                new String[]{"!", "plugins"},
//                new String[]{"!", "plugins"},
//                new int[]{0, 1},
//                new String[] {"name"},
//                new int[] {2}
//        );
//
//        return new AbstractMap.SimpleImmutableEntry<>(place, place);
//    }

    @Provides @Singleton Plugin.Presenter presenter(PluginPresenter presenter) {
        return presenter;
    }

    @Provides @Singleton Plugin.View view(PluginView view) {
        return view;
    }
}

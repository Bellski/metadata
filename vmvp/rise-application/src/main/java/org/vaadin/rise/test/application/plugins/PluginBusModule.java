package org.vaadin.rise.test.application.plugins;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import org.vaadin.rise.place.annotation.BusPlace;
import org.vaadin.rise.place.annotation.BusPlaces;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.test.application.plugins.someplugin.SomePluginModule;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Module(includes = SomePluginModule.class)
public class PluginBusModule {

    @Provides @Singleton @BusPlaces static Map<Place, Place> placeMap(@BusPlace Set<Map.Entry<Place, Place>> entries) {
        Map<Place, Place> placeMap = new LinkedHashMap<>(entries.size());
        for (Map.Entry<Place, Place> entry : entries) {
            placeMap.put(entry.getKey(), entry.getValue());
        }
        return placeMap;
    }


    @Provides @Singleton @BusPlace @ElementsIntoSet static Set<Map.Entry<Place, Place>> places() {
        return Collections.emptySet();
    }
}

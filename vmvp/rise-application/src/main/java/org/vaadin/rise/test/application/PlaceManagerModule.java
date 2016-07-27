package org.vaadin.rise.test.application;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.BasePlaceManager;
import org.vaadin.rise.place.PageUriFragmentSource;
import org.vaadin.rise.place.annotation.NameTokens;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;
import org.vaadin.rise.test.application.plugins.PluginBus;
import org.vaadin.rise.test.application.plugins.PluginBusModule;

import javax.inject.Singleton;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Module(includes = PluginBusModule.class)
public class PlaceManagerModule {

    @Provides
    @Singleton
    static Map<Place, Place> placeMap(Set<Map.Entry<Place, Place>> entries) {
        Map<Place, Place> placeMap = new LinkedHashMap<>(entries.size());
        for (Map.Entry<Place, Place> entry : entries) {
            placeMap.put(entry.getKey(), entry.getValue());
        }
        return placeMap;
    }

    @Provides @Singleton static PlaceManager placeManager(Map<Place, Place> placeMap, @NameTokens Map<String, String> places, UriFragmentSource uriFragmentSource, PluginBus pluginBus) {
        return new BasePlaceManager(
                placeMap,
                places,
                pluginBus,
                uriFragmentSource
        );
    }

    @Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
        return pageUriFragmentSource;
    }
}
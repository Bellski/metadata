package org.vaadin.rise.place.dagger;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;
import org.vaadin.rise.place.BasePlaceManager;
import org.vaadin.rise.place.BaseUriFragmentSource;
import org.vaadin.rise.place.PageUriFragmentSource;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 25.07.2016.
 */

public class PlaceManagerModule {

//    @Provides @Singleton static Map<Place, Place> placeMap(Set<Map.Entry<Place, Place>> entries) {
//        Map<Place, Place> placeMap = new LinkedHashMap<>(entries.size());
//        for (Map.Entry<Place, Place> entry : entries) {
//            placeMap.put(entry.getKey(), entry.getValue());
//        }
//        return placeMap;
//    }
//
//    @Provides @Singleton @Places static Map<String, String> placesMap(Set<String> places) {
//        Map<String, String> placeMap = new LinkedHashMap<>(places.size());
//        for (String entry : places) {
//            placeMap.put(entry, entry);
//        }
//        return placeMap;
//    }
//
//    @Provides @Singleton @ElementsIntoSet static Set<String> places() {
//        return Collections.emptySet();
//    }
//
//    @Provides @Singleton static PlaceManager placeManager(Map<Place, Place> placeMap, @Places Map<String, String> places, UriFragmentSource uriFragmentSource) {
//        return new BasePlaceManager(
//                placeMap,
//                places,
//                uriFragmentSource
//        );
//    }
//
//    @Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
//        return pageUriFragmentSource;
//    }
}

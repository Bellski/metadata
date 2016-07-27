package org.vaadin.rise.test.application.plugins;

import org.vaadin.rise.place.annotation.BusPlaces;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;
import org.vaadin.rise.place.util.PlaceUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Singleton
public class PluginBus implements PlaceBus {
    private Map<String, Place> pluginPlaces;
    private Set<String> places = new HashSet<>();

    @Inject
    public PluginBus(@BusPlaces Map<String, Place> pluginPlaces) {
        this.pluginPlaces = pluginPlaces;

        for (Place place : pluginPlaces.values()) {
            places.add(place.getNameToken());
        }
    }

    @Override
    public Place getPlace(String compareToPlace) {
        return pluginPlaces.get(PlaceUtils.convertToPlaceString(compareToPlace, places));
    }
}

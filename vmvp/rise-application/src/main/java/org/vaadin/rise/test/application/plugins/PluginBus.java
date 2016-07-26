package org.vaadin.rise.test.application.plugins;

import org.vaadin.rise.place.CompareToPlace;
import org.vaadin.rise.place.annotation.BusPlaces;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
@Singleton
public class PluginBus implements PlaceBus {
    private Map<Place, Place> pluginPlaces;
    private Map<String, String> places = new HashMap<>();

    @Inject
    public PluginBus(@BusPlaces Map<Place, Place> pluginPlaces) {
        this.pluginPlaces = pluginPlaces;

        for (Place place : pluginPlaces.values()) {
            for (String s : place.getNameTokens()) {
                places.put(s, s);
            }
        }
    }

    @Override
    public Place getPlace(String compareToPlace) {
        return pluginPlaces.get(new CompareToPlace(compareToPlace, places));
    }
}

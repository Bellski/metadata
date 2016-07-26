package org.vaadin.rise2.test.place;

import org.vaadin.rise.place.CompareToPlace;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class FeatureDirectory implements PlaceBus {
    private final Map<Place, Place> placeMap = new HashMap<>(); {
//        placeMap.put(SinglePlace.create("!/{userName}/profile"), SinglePlace.create("!/{userName}/profile"));
    }

    private final Map<String, String> tokenNameMap = new HashMap<>(); {
        tokenNameMap.put("profile", "profile");
        tokenNameMap.put("!", "!");
    }


    @Override
    public Place getPlace(String compareToPlace) {
        return placeMap.get(new CompareToPlace(compareToPlace, tokenNameMap));
    }
}

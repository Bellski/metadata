package org.vaadin.rise2.test.place;

import org.vaadin.rise.place.BasePlaceManager;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.UriFragmentSource;

import java.util.Map;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class FakePlaceManager extends BasePlaceManager {
    protected String fragmentUri;
    protected String errorPlace;

    public FakePlaceManager(@Places Map<Place, Place> placeMap, Map<String, String> nameTokenMap, UriFragmentSource uriFragmentSource) {
        super(placeMap, nameTokenMap, uriFragmentSource);
    }

    @Override
    public void onUriFragmentChanged(String fragmentUri) {
        super.onUriFragmentChanged(fragmentUri);
        this.fragmentUri = fragmentUri;
    }


    @Override
    public void revealErrorPlace(String invalidHistoryToken) {
        this.errorPlace = invalidHistoryToken;
    }
}

package org.vaadin.rise2.test.place;

import org.vaadin.rise.place.BasePlaceManager;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.UriFragmentSource;
import org.vaadin.rise2.test.dummy.FakeErrorManager;

import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class FakePlaceManager extends BasePlaceManager {
    protected String fragmentUri;
    protected String errorPlace;

    public FakePlaceManager(Map<String, Place> placeMap, Set<String> nameTokenMap, UriFragmentSource uriFragmentSource) {
        super(placeMap, nameTokenMap, uriFragmentSource, new FakeErrorManager());
    }

    @Override
    public void onUriFragmentChanged(String fragmentUri) {
        super.onUriFragmentChanged(fragmentUri);
        this.fragmentUri = fragmentUri;
    }


}

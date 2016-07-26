package org.vaadin.rise2.test.place;

import org.junit.Assert;
import org.junit.Test;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise2.test.dummy.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class PlaceTest {


    @Test
    public void goToPlaceTest() throws Exception {
        final DummyUI dummyUI = new DummyUI();

        final DummyRootSlot dummyRootSlot = new DummyRootSlot();
        dummyRootSlot.setLazyPresenter(() -> new DummyRootPresenter(new DummyRootView(dummyUI, dummyRootSlot)));

        final DummyPresenter dummyPresenter = new DummyPresenter(new DummyView(dummyUI), dummyRootSlot);
        final DummyPlace dummyPlace = new DummyPlace("!/lib", new LazyPlacePresenter<>(() -> dummyPresenter));

        final Map<Place, Place> placeMatcherStringMap = new HashMap<>(); {
            placeMatcherStringMap.put(dummyPlace, dummyPlace);
        }

        final Map<String, String> placeNameMap = new HashMap<>(); {
            placeNameMap.put("!", "!");
            placeNameMap.put("lib", "lib");
        }

        final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
        final FakePlaceManager fakePlaceManager = new FakePlaceManager(placeMatcherStringMap, placeNameMap, fakeUriFragmentSource);

        fakePlaceManager.onUriFragmentChanged("!/lib");

        Assert.assertTrue(dummyPresenter.haveBeenPrepared);
    }
}

package org.vaadin.rise2.test.place;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.place.BasePlaceManager;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise2.test.dummy.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class PlaceManagerTest {
    private static String PLACE_1 = "!/book/{name}";
    private static String PLACE_2 = "!/book/{name}/{page}";
    private static String PLACE_3 = "!/lib/books/";
    private static String PLACE_4 = "!/user/{userName}/profile";
    private static String PLACE_5 = "!/user/{userName}";


    /**
     * //TODO:
     * @throws Exception
     */
    @Test
    public void testPlaceMatch() throws Exception {
        final DummyUI dummyUI = new DummyUI();

        final DummyRootSlot dummyRootSlot = new DummyRootSlot();

        final DummyPresenter dummyPresenter = new DummyPresenter(new DummyView(dummyUI), dummyRootSlot);

        dummyRootSlot.setLazyPresenter(() -> new DummyRootPresenter(new DummyRootView(dummyUI, dummyRootSlot)));


        final DummyPlace dummyPlace1 = new DummyPlace(PLACE_1, new LazyPlacePresenter<>(() -> dummyPresenter));
        final DummyPlace dummyPlace2 = new DummyPlace(PLACE_2, new LazyPlacePresenter<>(() -> dummyPresenter));
        final DummyPlace dummyPlace3 = new DummyPlace(PLACE_3, new LazyPlacePresenter<>(() -> dummyPresenter));
        final DummyPlace dummyPlace4 = new DummyPlace(PLACE_4, new LazyPlacePresenter<>(() -> dummyPresenter));
        final DummyPlace dummyPlace5 = new DummyPlace(PLACE_5, new LazyPlacePresenter<>(() -> dummyPresenter));


        final Map<Place, Place> placeMatcherStringMap = new HashMap<>();
        {
            placeMatcherStringMap.put(dummyPlace1, dummyPlace1);
            placeMatcherStringMap.put(dummyPlace2, dummyPlace2);
            placeMatcherStringMap.put(dummyPlace3, dummyPlace3);
            placeMatcherStringMap.put(dummyPlace4, dummyPlace4);
            placeMatcherStringMap.put(dummyPlace5, dummyPlace5);
        }

        final Map<String, String> placeNameMap = new HashMap<>(); {
            placeNameMap.put("!", "!");
            placeNameMap.put("book", "book");
            placeNameMap.put("lib", "lib");
            placeNameMap.put("user", "user");
            placeNameMap.put("profile", "profile");
            placeNameMap.put("books", "books");
        }

        final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
        final FakePlaceManager fakePlaceManager = new FakePlaceManager(placeMatcherStringMap, placeNameMap, fakeUriFragmentSource);

        fakeUriFragmentSource.fireUriFragmentChange("!/book/java");

        Assert.assertEquals(
                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
                        .Builder()
                        .nameToken("!/book/{name}")
                        .with("name", "java")
                        .build()
        );

        fakeUriFragmentSource.fireUriFragmentChange("!/book/java/10");

        Assert.assertEquals(
                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
                        .Builder()
                        .nameToken("!/book/{name}/{page}")
                        .with("name", "java")
                        .with("page", "10")
                        .build()
        );

        fakeUriFragmentSource.fireUriFragmentChange("!/lib/books/");

        Assert.assertEquals(
                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
                        .Builder()
                        .nameToken("!/lib/books/")
                        .build()
        );

        fakeUriFragmentSource.fireUriFragmentChange("!/user/bellski/profile");

        Assert.assertEquals(
                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
                        .Builder()
                        .nameToken("!/user/{userName}/profile")
                        .with("userName", "bellski")
                        .build()
        );

        fakeUriFragmentSource.fireUriFragmentChange("!/user/bellski");

        Assert.assertEquals(
                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
                        .Builder()
                        .nameToken("!/user/{userName}")
                        .with("userName", "bellski")
                        .build()
        );

    }


    public void testPlaceBus() throws Exception {
        final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
        final BasePlaceManager fakePlaceManager = new BasePlaceManager(new HashMap<>(), new HashMap<>(), new FeatureDirectory(), fakeUriFragmentSource);

        fakeUriFragmentSource.fireUriFragmentChange("!/bellski/profile");

        Assert.assertEquals(
                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
                        .Builder()
                        .nameToken("!/{userName}/profile")
                        .with("userName", "bellski")
                        .build()
        );
    }
}

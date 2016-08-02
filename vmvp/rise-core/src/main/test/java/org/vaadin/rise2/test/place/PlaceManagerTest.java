package org.vaadin.rise2.test.place;

import org.junit.Assert;
import org.junit.Test;
import org.vaadin.rise.place.LazyPlacePresenter;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.PlaceRequest;
import org.vaadin.rise2.test.dummy.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class PlaceManagerTest {
	private static String PLACE_1 = "!/book/{name}";
	private static String PLACE_2 = "!/book/{name}/{page}";
	private static String PLACE_3 = "!/lib/books";
	private static String PLACE_4 = "!/user/{userName}/profile";
	private static String PLACE_5 = "!/user/{userName}";


	/**
	 * //TODO:
	 *
	 * @throws Exception
	 */
	@Test
	public void testPlaceMatch() throws Exception {
		final DummyUI dummyUI = new DummyUI();

		final DummyRootSlot dummyRootSlot = new DummyRootSlot();

		final DummyPresenter dummyPresenter = new DummyPresenter(new DummyView(dummyUI), dummyRootSlot);

		dummyRootSlot.setLazyPresenter(() -> new DummyRootPresenter(new DummyRootView(dummyUI, dummyRootSlot)));


		final DummyPlace dummyPlace1 =
			new DummyPlace(new LazyPlacePresenter<>(() -> dummyPresenter), PLACE_1, "!/book/?", new String[]{"name"}, new int[]{2});

		final DummyPlace dummyPlace2 =
			new DummyPlace(new LazyPlacePresenter<>(() -> dummyPresenter), PLACE_2, "!/book/?/?", new String[]{"name", "page"}, new int[]{2, 3});

		final DummyPlace dummyPlace3 =
			new DummyPlace(new LazyPlacePresenter<>(() -> dummyPresenter), PLACE_3, "!/lib/books", null, null);

		final DummyPlace dummyPlace4 =
			new DummyPlace(new LazyPlacePresenter<>(() -> dummyPresenter), PLACE_4, "!/user/?/profile", new String[]{"userName"}, new int[]{2});

		final DummyPlace dummyPlace5 =
			new DummyPlace(new LazyPlacePresenter<>(() -> dummyPresenter), PLACE_5, "!/user/?", new String[]{"userName"}, new int[]{2});


		final Map<String, Place> placeMatcherStringMap = new HashMap<>();
		{
			placeMatcherStringMap.put("!/book/?", dummyPlace1);
			placeMatcherStringMap.put("!/book/?/?", dummyPlace2);
			placeMatcherStringMap.put("!/lib/books", dummyPlace3);
			placeMatcherStringMap.put("!/user/?/profile", dummyPlace4);
			placeMatcherStringMap.put("!/user/?", dummyPlace5);
		}


		final Set<String> placeNameMap = new HashSet<>();
		{
			placeNameMap.add("!");
			placeNameMap.add("book");
			placeNameMap.add("lib");
			placeNameMap.add("user");
			placeNameMap.add("profile");
			placeNameMap.add("books");
		}

		final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
		final FakePlaceManager fakePlaceManager = new FakePlaceManager(placeMatcherStringMap, placeNameMap, fakeUriFragmentSource);

//		fakeUriFragmentSource.fireUriFragmentChange("!/lib/books");
//
//		Assert.assertEquals(
//			fakePlaceManager.getCurrentPlaceRequest(),
//			new PlaceRequest
//				.Builder()
//				.nameToken("!/lib/books")
//				.build()
//		);


		fakeUriFragmentSource.fireUriFragmentChange("!/book/java");

		Assert.assertEquals(
			fakePlaceManager.getCurrentPlaceRequest(),
			new PlaceRequest
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


//    public void testPlaceBus() throws Exception {
//        final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
//        final BasePlaceManager fakePlaceManager = new BasePlaceManager(new HashMap<>(), new HashMap<>(), new FeatureDirectory(), fakeUriFragmentSource);
//
//        fakeUriFragmentSource.fireUriFragmentChange("!/bellski/profile");
//
//        Assert.assertEquals(
//                fakePlaceManager.getCurrentPlaceRequest(), new PlaceRequest
//                        .Builder()
//                        .nameToken("!/{userName}/profile")
//                        .with("userName", "bellski")
//                        .build()
//        );
//    }

	@Test
	public void testRevealDefaultPlace() {
		final DummyUI dummyUI = new DummyUI();

		final DummyRootSlot dummyRootSlot = new DummyRootSlot();

		final DummyPresenter dummyPresenter = new DummyPresenter(new DummyView(dummyUI), dummyRootSlot);

		dummyRootSlot.setLazyPresenter(() -> new DummyRootPresenter(new DummyRootView(dummyUI, dummyRootSlot)));


		final DummyPlace defailtPlace =
			new DummyPlace(new LazyPlacePresenter<>(() -> dummyPresenter), "!/book", "!/book", null, null);

		final Map<String, Place> stringPlaceMap = new HashMap<>();
		{
			stringPlaceMap.put("!/book", defailtPlace);
		}

		final Set<String> places = new HashSet<>();
		{
			places.add("!");
			places.add("book");
		}

		final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
		final DefaultPlaceManager defaultPlaceManager = new DefaultPlaceManager(stringPlaceMap, places, fakeUriFragmentSource, "!/book", "!/book", new FakeErrorManager());

		defaultPlaceManager.revealDefaultPlace();

		Assert.assertTrue(dummyPresenter.haveBeenPrepared);
	}
}

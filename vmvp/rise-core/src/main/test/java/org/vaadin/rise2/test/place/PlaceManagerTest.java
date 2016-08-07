package org.vaadin.rise2.test.place;

import dagger.Lazy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.PlaceRequest;
import org.vaadin.rise.slot.SlotRevealBus;
import org.vaadin.rise2.test.dummy.*;
import org.vaadin.rise2.test.dummy.place.DummyPlaceModule;
import org.vaadin.rise2.test.dummy.place.DummyPresenter;
import org.vaadin.rise2.test.dummy.place.DummyView;
import org.vaadin.rise2.test.dummy.root.DummyRootModule;

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

	private SlotRevealBus slotRevealBus;
	private Map<String, Place> placeMap;


	@Before
	public void before() throws Exception {
		slotRevealBus = new SlotRevealBus();

		DummyRootModule.lazyPresenterProvider(slotRevealBus);

		placeMap = new HashMap<>();
	}

	/**
	 * //TODO:
	 *
	 * @throws Exception
	 */
	@Test
	public void testPlaceMatch() throws Exception {
		final DummyPlaceModule dummyPlaceModule = new DummyPlaceModule();
		dummyPlaceModule.place(slotRevealBus, PLACE_1, "!/book/?", new String[]{"name"}, new int[]{2}, placeMap);
		dummyPlaceModule.place(slotRevealBus, PLACE_2, "!/book/?/?", new String[]{"name", "page"}, new int[]{2, 3}, placeMap);
		dummyPlaceModule.place(slotRevealBus, PLACE_3, "!/lib/books", null, null, placeMap);
		dummyPlaceModule.place(slotRevealBus, PLACE_4, "!/user/?/profile", new String[]{"userName"}, new int[]{2}, placeMap);
		dummyPlaceModule.place(slotRevealBus, PLACE_5, "!/user/?", new String[]{"userName"}, new int[]{2}, placeMap);


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
		final FakePlaceManager fakePlaceManager = new FakePlaceManager(placeMap, placeNameMap, fakeUriFragmentSource);

		fakeUriFragmentSource.fireUriFragmentChange("!/lib/books");

		Assert.assertEquals(
			fakePlaceManager.getCurrentPlaceRequest(),
			new PlaceRequest
				.Builder()
				.nameToken("!/lib/books")
				.build()
		);


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
//		final DummyUI dummyUI = new DummyUI();
//
//		final DummyRootSlot dummyRootSlot = new DummyRootSlot();
//
//		final DummyPresenter dummyPresenter = new DummyPresenter(new DummyView(dummyUI), dummyRootSlot);
//
//		dummyRootSlot.setLazyPresenter(() -> new DummyRootPresenter(new DummyRootView(dummyUI, dummyRootSlot)));
//
//
//		final DummyPlace defailtPlace =
//			new DummyPlace(() -> dummyPresenter, "!/book", "!/book", null, null);
//
//		final Map<String, Place> stringPlaceMap = new HashMap<>();
//		{
//			stringPlaceMap.put("!/book", defailtPlace);
//		}
//
//		final Set<String> places = new HashSet<>();
//		{
//			places.add("!");
//			places.add("book");
//		}
//
//		final FakeUriFragmentSource fakeUriFragmentSource = new FakeUriFragmentSource();
//		final DefaultPlaceManager defaultPlaceManager = new DefaultPlaceManager(stringPlaceMap, places, fakeUriFragmentSource, "!/book", "!/book", new FakeErrorManager());
//
//		defaultPlaceManager.revealDefaultPlace();
//
//		Assert.assertTrue(dummyPresenter.haveBeenPrepared);
	}
}

package org.vaadin.rise2.test.place;

import org.junit.Before;
import org.junit.Test;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.slot.SlotRevealBus;
import org.vaadin.rise2.test.dummy.root.DummyRootModule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aleksandr on 07.08.2016.
 */
public class RevealTest {
    private SlotRevealBus slotRevealBus;
    private Map<String, Place> placeMap;


    @Before
    public void before() throws Exception {
        slotRevealBus = new SlotRevealBus();

        DummyRootModule.lazyPresenterProvider(slotRevealBus);

        placeMap = new HashMap<>();
    }

    @Test
    public void revealTest() throws Exception {

    }
}

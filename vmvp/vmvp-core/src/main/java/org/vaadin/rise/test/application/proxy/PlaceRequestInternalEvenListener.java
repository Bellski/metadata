package org.vaadin.rise.test.application.proxy;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import org.vaadin.rise.test.application.proxy.events.PlaceRequestInternalEvent;


/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface PlaceRequestInternalEvenListener {
    @Subscribe
    void onPlaceRequestInternalEven(PlaceRequestInternalEvent event);
}

package org.vaadin.rise.place.event;

import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import org.vaadin.rise.core.event.RiseEventHandler;

public interface PlaceRequestInternalHandler  extends RiseEventHandler {
    @Subscribe
    void onPlaceRequest(PlaceRequestInternalEvent event);
}

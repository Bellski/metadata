package org.vaadin.rise.place.event;

import org.vaadin.rise.core.event.RiseEventHandler;

public interface PlaceRequestInternalHandler  extends RiseEventHandler {
    void onPlaceRequest(PlaceRequestInternalEvent event);
}

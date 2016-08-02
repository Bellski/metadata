package org.vaadin.rise.place.api;

import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.place.reveal.Supplier;
import org.vaadin.rise.security.Gatekeeper;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public interface Place extends ComparablePlace {
    String[] getParamNames();

    int[] getParamIndexes();

    boolean hasParameters();

    boolean canReveal();

    String getFallBackNamePlace();

    void replaceGateKeeper(Gatekeeper gatekeeper);

    void reveal(PlaceRequest placeRequest, Supplier supplier);

    String getNameToken();
}

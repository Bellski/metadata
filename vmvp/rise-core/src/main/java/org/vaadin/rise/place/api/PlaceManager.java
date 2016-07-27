package org.vaadin.rise.place.api;

import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.place.deprecated.token.TokenFormatException;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public interface PlaceManager {
    PlaceRequest getCurrentPlaceRequest();

    void revealCurrentPlace();

    void revealPlace(PlaceRequest request, boolean updateBrowserUrl);

    void revealUnauthorizedPlace(String unauthorizedHistoryToken);

    void updateHistory(PlaceRequest request, boolean updateBrowserUrl);

    String toPlaceToken(PlaceRequest placeRequest) throws TokenFormatException;

    void revealDefaultPlace();

    void revealErrorPlace(String invalidHistoryToken);
}

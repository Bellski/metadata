package org.vaadin.rise.test.application.proxy;


import org.vaadin.rise.test.application.navigation.PlaceRequest;

import java.util.List;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface PlaceManager {
    /**
     * Access the current place request, that is, the tail of the place request hierarchy. If the
     * hierarchy is empty this returns an empty {@link PlaceRequest}.
     *
     * @return The current {@link PlaceRequest}, or an empty one if the hierarchy is empty.
     * @see #getCurrentPlaceHierarchy()
     */
    PlaceRequest getCurrentPlaceRequest();

    /**
     * Access the current place hierarchy, with the current {@link PlaceRequest} being the last
     * element of this list.
     *
     * @return The current {@link PlaceRequest}.
     * @see #getCurrentPlaceRequest()
     */
    List<PlaceRequest> getCurrentPlaceHierarchy();

    void revealCurrentPlace();

    void revealDefaultPlace();
    void revealErrorPlace(String invalidToken);
    void revealUnauthorizedPlace(String token);

    void revealPlace(PlaceRequest request, boolean updateBrowserUrl);
    void revealPlace(PlaceRequest request);
}

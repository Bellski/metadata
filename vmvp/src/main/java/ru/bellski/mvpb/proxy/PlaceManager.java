package ru.bellski.mvpb.proxy;

import ru.bellski.mvpb.navigation.PlaceRequest;

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
}

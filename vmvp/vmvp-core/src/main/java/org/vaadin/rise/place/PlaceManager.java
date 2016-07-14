package org.vaadin.rise.place;

import org.vaadin.rise.core.event.RiseEventBus;

import java.util.List;

/**
 * Created by oem on 7/12/16.
 */
public interface PlaceManager {
    String buildHistoryToken(PlaceRequest request);
    String buildRelativeHistoryToken(int level);
    String buildRelativeHistoryToken(PlaceRequest request);
    String buildRelativeHistoryToken(PlaceRequest request, int level);
    List<PlaceRequest> getCurrentPlaceHierarchy();
    PlaceRequest getCurrentPlaceRequest();
    RiseEventBus getEventBus();
    int getHierarchyDepth();
    void navigateBack();
    void updateHistory(PlaceRequest request, boolean updateBrowserUrl);
    void revealCurrentPlace();
    void revealDefaultPlace();
    void revealErrorPlace(String invalidHistoryToken);
    void revealUnauthorizedPlace(String unauthorizedHistoryToken);
    void setOnLeaveConfirmation(String question);
    void revealPlace(PlaceRequest request);
    void revealPlace(PlaceRequest request, boolean updateBrowserUrl);
    void revealPlaceHierarchy(List<PlaceRequest> placeRequestHierarchy);
    void revealRelativePlace(int level);
    void revealRelativePlace(PlaceRequest request);
    void revealRelativePlace(PlaceRequest request, int level);
    void unlock();
    boolean hasPendingNavigation();
}

package org.vaadin.rise.place.deprecated.token;

import org.vaadin.rise.place.deprecated.PlaceRequest;

import java.util.List;

public interface TokenFormatter {
    String toHistoryToken(List<PlaceRequest> placeRequestHierarchy) throws TokenFormatException;

    PlaceRequest toPlaceRequest(String placeToken) throws TokenFormatException;

    List<PlaceRequest> toPlaceRequestHierarchy(String historyToken) throws TokenFormatException;

    String toPlaceToken(PlaceRequest placeRequest) throws TokenFormatException;
}
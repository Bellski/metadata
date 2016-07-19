package org.vaadin.rise.place;

import com.vaadin.server.Page;
import org.vaadin.rise.place.token.RouteTokenFormatter;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class DefaultPlaceManager extends PlaceManagerImpl {
    private final PlaceRequest defaultPlaceRequest;
    private final PlaceRequest errorPlaceRequest;
    private final PlaceRequest unauthorizedPlaceRequest;


    public DefaultPlaceManager(RouteTokenFormatter tokenFormatter, Page page, String defaultPlaceNameToken, String errorPlaceNameToken, String unauthorizedPlaceNameToken) {
        super(tokenFormatter, page);

        defaultPlaceRequest = new PlaceRequest.Builder().nameToken(defaultPlaceNameToken).build();
        errorPlaceRequest = new PlaceRequest.Builder().nameToken(errorPlaceNameToken).build();
        unauthorizedPlaceRequest = new PlaceRequest.Builder().nameToken(unauthorizedPlaceNameToken).build();
    }

    @Override
    public void revealDefaultPlace() {
        if (getBrowserHistoryToken() == null || getBrowserHistoryToken().isEmpty()) {
            revealPlace(defaultPlaceRequest, false);
        } else {
            doRevealPlace(new PlaceRequest.Builder().nameToken(getBrowserHistoryToken()).build(), false);
        }
    }

    @Override
    public void revealErrorPlace(String invalidHistoryToken) {
        revealPlace(errorPlaceRequest, false);
    }

    @Override
    public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
        revealPlace(unauthorizedPlaceRequest, false);
    }
}

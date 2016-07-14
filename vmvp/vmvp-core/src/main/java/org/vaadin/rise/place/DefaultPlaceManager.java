package org.vaadin.rise.place;

import com.vaadin.server.Page;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.place.token.TokenFormatter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class DefaultPlaceManager extends PlaceManagerImpl {
    private final PlaceRequest defaultPlaceRequest;
    private final PlaceRequest errorPlaceRequest;
    private final PlaceRequest unauthorizedPlaceRequest;

    public DefaultPlaceManager(RiseEventBus eventBus, TokenFormatter tokenFormatter, Page page, String defaultPlaceNameToken, String errorPlaceNameToken, String unauthorizedPlaceNameToken) {
        super(eventBus, tokenFormatter, page);

        defaultPlaceRequest = new PlaceRequest.Builder().nameToken(defaultPlaceNameToken).build();
        errorPlaceRequest = new PlaceRequest.Builder().nameToken(errorPlaceNameToken).build();
        unauthorizedPlaceRequest = new PlaceRequest.Builder().nameToken(unauthorizedPlaceNameToken).build();
    }

    @Override
    public void revealDefaultPlace() {
        revealPlace(defaultPlaceRequest, false);
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

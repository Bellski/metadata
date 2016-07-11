package ru.bellski.vmvp.proxy;

import com.vaadin.server.Page;
import ru.bellski.vmvp.annotations.DefaultPlace;
import ru.bellski.vmvp.annotations.ErrorPlace;
import ru.bellski.vmvp.annotations.UnauthorizedPlace;
import ru.bellski.vmvp.navigation.PlaceRequest;
import ru.bellski.vmvp.navigation.token.TokenFormatter;

import javax.inject.Inject;

public class DefaultPlaceManager extends PlaceManagerImpl {
    private final PlaceRequest defaultPlaceRequest;
    private final PlaceRequest errorPlaceRequest;
    private final PlaceRequest unauthorizedPlaceRequest;

    @Inject
    public DefaultPlaceManager(Page page,
                               VMVPEventBus vmvpEventBus,
                               TokenFormatter tokenFormatter,
                               @DefaultPlace String defaultPlaceNameToken,
                               @ErrorPlace String errorPlaceNameToken,
                               @UnauthorizedPlace String unauthorizedPlaceNameToken) {

        super(page, vmvpEventBus, tokenFormatter);

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
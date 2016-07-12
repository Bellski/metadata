package org.vaadin.rise.test.application.proxy;

import dagger.Lazy;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;
import org.vaadin.rise.test.application.navigation.PlaceRequest;


import javax.inject.Inject;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public class ProxyPlaceImpl<PRESENTER_IMPL extends VMVPPresenterImpl<?, ?>> extends ProxyImpl<PRESENTER_IMPL> implements ProxyPlace<PRESENTER_IMPL> {
    private final String nameToken;

    @Inject
    PlaceManager placeManager;

    public ProxyPlaceImpl(VMVPEventBus vmvpEventBus, Lazy<PRESENTER_IMPL> lazyPresenter, String nameToken) {
        super(vmvpEventBus, lazyPresenter);
        this.nameToken = nameToken;
        vmvpEventBus.register((PlaceRequestInternalEvenListener) event -> {
            final PlaceRequest request = event.getRequest();
            if (matchesRequest(request)) {
                if (canReveal()){
                    handleRequest(request, true);
                }
            }
        });
    }

    @Override
    public boolean canReveal() {
        return true;
    }

    @Override
    public String getNameToken() {
        return nameToken;
    }

    @Override
    public boolean matchesRequest(PlaceRequest request) {
        return request.matchesNameToken(nameToken);
    }

    private void handleRequest(final PlaceRequest request, final boolean updateBrowserUrl) {
        final PRESENTER_IMPL presenter = getPresenter();
        PlaceRequest originalRequest = placeManager.getCurrentPlaceRequest();

        if (originalRequest == placeManager.getCurrentPlaceRequest()) {

        }
    }
}

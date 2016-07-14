package org.vaadin.rise.proxy;

import com.google.web.bindery.event.shared.Event;
import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.place.Place;
import org.vaadin.rise.place.PlaceManager;
import org.vaadin.rise.place.PlaceRequest;
import org.vaadin.rise.place.event.PlaceRequestInternalEvent;
import org.vaadin.rise.place.event.PlaceRequestInternalHandler;

public class ProxyPlaceImpl<PRESENTER extends RisePresenterImpl<?>>  implements Proxy<PRESENTER>, ProxyPlace<PRESENTER> {
    private Place place;
    private PlaceManager placeManager;

    private final Lazy<PRESENTER> presenter;
    private final RiseEventBus eventBus;

    public ProxyPlaceImpl(Place place, PlaceManager placeManager, Lazy<PRESENTER> cas1Presenter, RiseEventBus eventBus) {
        this.place = place;
        this.placeManager = placeManager;
        this.presenter = cas1Presenter;
        this.eventBus = eventBus;

        eventBus.addHandler(PlaceRequestInternalEvent.getType(), event -> {
            if (event.isHandled()) {
                return;
            }
            PlaceRequest request = event.getRequest();
            if (matchesRequest(request)) {
                event.setHandled();
                if (canReveal()) {
                    handleRequest(request, event.shouldUpdateBrowserHistory());
                } else {
                    event.setUnauthorized();
                }
            }
        });
    }

    @Override
    public void fireEvent(Event<?> event) {
        eventBus.fireEvent(event);
    }


    @Override
    public RiseEventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PRESENTER getPresenter() {
        return presenter.get();
    }

    private void handleRequest(final PlaceRequest request, final boolean updateBrowserUrl) {
        final PRESENTER thatPresenter = presenter.get();

        PlaceRequest originalRequest = placeManager.getCurrentPlaceRequest();
//        presenter.prepareFromRequest(request);
        if (originalRequest == placeManager.getCurrentPlaceRequest()) {
            placeManager.updateHistory(request, updateBrowserUrl);
        }

        if (!thatPresenter.useManualReveal()) {
            // Automatic reveal
            manualReveal(thatPresenter);
        }
    }

    @Override
    public void manualReveal(RisePresenterImpl<?> presenter) {
        if (!placeManager.hasPendingNavigation()) {
            if (!presenter.isVisible()) {
                // This will trigger a reset in due time
                presenter.forceReveal();
            } else {
                // We have to do the reset ourselves
//                ResetPresentersEvent.fire(this);
            }
        }
        placeManager.unlock();
    }

    @Override
    public void manualRevealFailed() {
        placeManager.unlock();
    }

    @Override
    public boolean canReveal() {
        return place.canReveal();
    }

    @Override
    public String getNameToken() {
        return place.getNameToken();
    }

    @Override
    public boolean matchesRequest(PlaceRequest request) {
        return place.matchesRequest(request);
    }
}
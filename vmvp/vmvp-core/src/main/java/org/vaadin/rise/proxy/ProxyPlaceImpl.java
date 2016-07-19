package org.vaadin.rise.proxy;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.Place;
import org.vaadin.rise.place.PlaceManager;
import org.vaadin.rise.place.PlaceRequest;

public class ProxyPlaceImpl<PRESENTER extends RisePresenterImpl<?>>  extends BaseProxy<PRESENTER> implements ProxyPlace<PRESENTER> {
    private Place place;
    private PlaceManager placeManager;

    private final Lazy<PRESENTER> presenter;

    public ProxyPlaceImpl(Place place, PlaceManager placeManager, Lazy<PRESENTER> cas1Presenter) {
		super(cas1Presenter);

        this.place = place;
        this.placeManager = placeManager;
        this.presenter = cas1Presenter;

        placeManager.addPlaceRequestInternalHandler(event -> {
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

    private void handleRequest(final PlaceRequest request, final boolean updateBrowserUrl) {
        final PRESENTER thatPresenter = presenter.get();

        PlaceRequest originalRequest = placeManager.getCurrentPlaceRequest();
//        getPresenter.prepareFromRequest(request);
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
package org.vaadin.rise.deprecated.proxy;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.CanReveal;
import org.vaadin.rise.place.deprecated.Place;
import org.vaadin.rise.place.deprecated.PlaceManager_TODO;
import org.vaadin.rise.place.deprecated.PlaceRequest;

public class ProxyPlaceImpl<PRESENTER extends RisePresenterImpl<?>>  extends BaseProxy<PRESENTER> implements ProxyPlace<PRESENTER> {
    private Place place;
    private PlaceManager_TODO placeManagerTODO;

    private final LazyPlacePresenter<PRESENTER> presenter;

    public ProxyPlaceImpl(Place place, PlaceManager_TODO placeManagerTODO, LazyPlacePresenter<PRESENTER> lazyPresenter) {
		super(lazyPresenter);

        this.place = place;
        this.placeManagerTODO = placeManagerTODO;
        this.presenter = lazyPresenter;

        placeManagerTODO.addPlaceRequestInternalHandler(event -> {
			if (event.isHandled()) {
				return;
			}
			PlaceRequest request = event.getRequest();
			if (matchesRequest(request)) {
				event.setHandled();
//				if (canReveal()) {
//					handleRequest(request, event.shouldUpdateBrowserHistory());
//				} else {
//					event.setUnauthorized();
//				}
			}
		});
    }

    private void handleRequest(final PlaceRequest request, final boolean updateBrowserUrl) {
        final PRESENTER thatPresenter = presenter.getLazyPresenter();

        PlaceRequest originalRequest = placeManagerTODO.getCurrentPlaceRequest();
		thatPresenter.prepareFromRequest(request);
        if (originalRequest == placeManagerTODO.getCurrentPlaceRequest()) {
            placeManagerTODO.updateHistory(request, updateBrowserUrl);
        }

        if (!thatPresenter.useManualReveal()) {
            // Automatic reveal
            manualReveal(thatPresenter);
        }
    }

    @Override
    public void manualReveal(RisePresenterImpl<?> presenter) {
        if (!placeManagerTODO.hasPendingNavigation()) {
            if (!presenter.isVisible()) {
                // This will trigger a reset in due time
                presenter.forceReveal();
            } else {
                // We have to do the reset ourselves
//                ResetPresentersEvent.fire(this);
            }
        }
        placeManagerTODO.unlock();
    }

    @Override
    public void manualRevealFailed() {
        placeManagerTODO.unlock();
    }

    @Override
    public CanReveal canReveal() {
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
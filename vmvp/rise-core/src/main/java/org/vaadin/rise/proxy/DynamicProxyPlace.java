package org.vaadin.rise.proxy;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.place.PlaceRequest;

/**
 * Created by oem on 7/21/16.
 */
public class DynamicProxyPlace<PRESENTER extends RisePresenterImpl<?>>  extends BaseProxy<PRESENTER> implements ProxyPlace<PRESENTER> {

	public DynamicProxyPlace(LazyPresenter<PRESENTER> lazyPresenter) {
		super(lazyPresenter);
	}

	@Override
	public void manualReveal(RisePresenterImpl<?> presenter) {

	}

	@Override
	public void manualRevealFailed() {

	}

	@Override
	public boolean canReveal() {
		return false;
	}

	@Override
	public String getNameToken() {
		return null;
	}

	@Override
	public boolean matchesRequest(PlaceRequest request) {
		return false;
	}
}

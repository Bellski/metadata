package org.vaadin.rise.deprecated.proxy;

import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by oem on 7/18/16.
 */
public class BaseProxy<PRESENTER extends RisePresenterImpl<?>> implements Proxy<PRESENTER> {
	private final LazyPlacePresenter<PRESENTER> lazyPresenter;

	public BaseProxy(LazyPlacePresenter<PRESENTER> lazyPresenter) {
		this.lazyPresenter = lazyPresenter;
	}

	@Override
	public PRESENTER getPresenter() {
		return lazyPresenter.getLazyPresenter();
	}
}

package org.vaadin.rise.proxy;

import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by oem on 7/18/16.
 */
public class BaseProxy<PRESENTER extends RisePresenterImpl<?>> implements Proxy<PRESENTER> {
	private final LazyPresenter<PRESENTER> lazyPresenter;

	public BaseProxy(LazyPresenter<PRESENTER> lazyPresenter) {
		this.lazyPresenter = lazyPresenter;
	}

	@Override
	public PRESENTER getPresenter() {
		return lazyPresenter.getLazyPresenter();
	}
}

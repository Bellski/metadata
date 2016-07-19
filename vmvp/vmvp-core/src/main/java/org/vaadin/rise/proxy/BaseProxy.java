package org.vaadin.rise.proxy;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by oem on 7/18/16.
 */
public class BaseProxy<PRESENTER extends RisePresenterImpl<?>> implements Proxy<PRESENTER> {
	private final Lazy<PRESENTER> lazyPresenter;

	public BaseProxy(Lazy<PRESENTER> lazyPresenter) {
		this.lazyPresenter = lazyPresenter;
	}

	@Override
	public PRESENTER getPresenter() {
		return lazyPresenter.get();
	}
}

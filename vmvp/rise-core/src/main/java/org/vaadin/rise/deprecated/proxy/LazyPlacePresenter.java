package org.vaadin.rise.deprecated.proxy;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/20/16.
 */
@Singleton
public class LazyPlacePresenter<Presenter_ extends RisePresenterImpl<?>> {
	private final Lazy<Presenter_> lazyPresenter;

	@Inject
	public LazyPlacePresenter(Lazy<Presenter_> lazyPresenter) {
		this.lazyPresenter = lazyPresenter;
	}

	public Presenter_ getLazyPresenter() {
		return lazyPresenter.get();
	}
}

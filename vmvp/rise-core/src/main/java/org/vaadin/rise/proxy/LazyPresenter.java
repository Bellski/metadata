package org.vaadin.rise.proxy;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/20/16.
 */
@Singleton
public class LazyPresenter<Presenter_ extends RisePresenterComponent<?>> {
	private final Lazy<Presenter_> lazyPresenter;

	@Inject
	public LazyPresenter(Lazy<Presenter_> lazyPresenter) {
		this.lazyPresenter = lazyPresenter;
	}

	public Presenter_ getLazyPresenter() {
		final Presenter_ presenter = lazyPresenter.get();
		if (!presenter.isInitialized()) {
			presenter.setInitialized();
		}

		return presenter;
	}
}

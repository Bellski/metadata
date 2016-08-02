package org.vaadin.rise.place.reveal;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;

/**
 * Created by oem on 8/2/16.
 */
public class Reveal<Presenter_ extends RisePresenterImpl<?>> {
	private LazyPlacePresenter<Presenter_> lazyPresenter;

	public Reveal(LazyPlacePresenter<Presenter_> lazyPresenter) {
		this.lazyPresenter = lazyPresenter;
	}

	public Reveal<Presenter_> onException(ExceptionFunction exceptionFunction) {
		return this;
	}
}

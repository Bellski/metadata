package org.vaadin.rise.place;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public class LazyPresenterProvider<Presenter_ extends RisePresenterImpl<?>> {

    private Lazy<Presenter_> lazyPresenter;

    public LazyPresenterProvider(Lazy<Presenter_> lazyPresenter) {

        this.lazyPresenter = lazyPresenter;
    }

    public Presenter_ getPresenter() {
        return lazyPresenter.get();
    }
}


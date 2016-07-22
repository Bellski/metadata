
package org.vaadin.rise.test.application.application.error;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.test.application.application.error.Error.Presenter;
import org.vaadin.rise.test.application.application.error.Error.View;

import javax.inject.Singleton;

@Module
public class RiseErrorModule extends ErrorModule {



    @Provides
    @Singleton
    Proxy<ErrorPresenter> providesRiseErrorPresenterProxy(RiseErrorPresenterProxy proxy) {
        return proxy;
    }

    @Provides
    @Singleton
    View providesErrorView(ErrorView view) {
        return view;
    }

    @Provides
    @Singleton
    Presenter providesErrorPresenter(ErrorPresenter presenter) {
        return presenter;
    }
}
package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import org.vaadin.rise.deprecated.proxy.Proxy;

@Module
public class RiseSubEntryModule extends SubEntryModule {

    @Provides
    @Singleton
    Proxy<SubEntryPresenter> providesRiseSubEntryPresenterProxy(RiseSubEntryPresenterProxy proxy) {
        return proxy;
    }

    @Provides @Singleton
    SubEntry.View providesSubEntryView(SubEntryView view) {
        return view;
    }

    @Provides @Singleton
    SubEntry.Presenter providesSubEntryPresenter(SubEntryPresenter presenter) {
        return presenter;
    }
}
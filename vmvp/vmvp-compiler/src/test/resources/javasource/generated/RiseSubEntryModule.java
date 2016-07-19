package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import javasource.SubEntry.Presenter;
import javasource.SubEntryView;
import javasource.SubEntryModule;
import javasource.RiseSubEntryPresenterProxy;
import javasource.SubEntry.View;
import javasource.SubEntryPresenter;

import org.vaadin.rise.proxy.Proxy;

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
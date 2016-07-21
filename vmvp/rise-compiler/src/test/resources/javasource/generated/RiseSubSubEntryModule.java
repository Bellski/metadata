package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import javasource.SubSubEntryView;
import javasource.SubSubEntryModule;
import javasource.RiseSubSubEntryPresenterProxy;
import javasource.SubSubEntry.View;
import javasource.SubSubEntry.Presenter;
import javasource.SubSubEntryPresenter;

import org.vaadin.rise.proxy.Proxy;

@Module
public class RiseSubSubEntryModule extends SubSubEntryModule {

    @Provides
    @Singleton
    Proxy<SubSubEntryPresenter> providesRiseSubSubEntryPresenterProxy(RiseSubSubEntryPresenterProxy proxy) {
        return proxy;
    }


    @Provides @Singleton
    SubSubEntry.View providesSubSubEntryView(SubSubEntryView view) {
        return view;
    }

    @Provides @Singleton
    SubSubEntry.Presenter providesSubSubEntryPresenter(SubSubEntryPresenter presenter) {
        return presenter;
    }
}
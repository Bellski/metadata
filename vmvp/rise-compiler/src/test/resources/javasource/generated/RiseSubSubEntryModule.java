package javasource;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.proxy.Proxy;

import javax.inject.Singleton;

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
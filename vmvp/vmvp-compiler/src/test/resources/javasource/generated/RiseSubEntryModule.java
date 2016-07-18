package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import javasource.SubEntryView;
import javasource.SubEntryModule;
import javasource.SubEntryPresenter;

@Module
public class RiseSubEntryModule extends SubEntryModule {



    @Provides @Singleton
    SubEntryView providesSubEntryView(SubEntryView view) {
        return view;
    }

    @Provides @Singleton
    SubEntryPresenter providesSubEntryPresenter(SubEntryPresenter presenter) {
        return presenter;
    }
}
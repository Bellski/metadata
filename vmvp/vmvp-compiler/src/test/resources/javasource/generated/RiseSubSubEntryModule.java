package javasource;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import javasource.SubSubEntryView;
import javasource.SubSubEntryModule;
import javasource.SubSubEntryPresenter;

@Module
public class RiseSubSubEntryModule extends SubSubEntryModule {


    @Provides @Singleton
    SubSubEntryView providesSubSubEntryView(SubSubEntryView view) {
        return view;
    }

    @Provides @Singleton
    SubSubEntryPresenter providesSubSubEntryPresenter(SubSubEntryPresenter presenter) {
        return presenter;
    }
}
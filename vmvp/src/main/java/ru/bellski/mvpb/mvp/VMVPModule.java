package ru.bellski.mvpb.mvp;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 10.07.2016.
 */
@Module
public abstract class VMVPModule<VIEW extends VMVPView, VIEWIMPL extends VMVPViewImpl, PRESENTER extends VMVPPresenter, PRESENTERIMPL extends VMVPPresenterComponent> {

    @Provides @Singleton
    public VIEW view(VIEWIMPL view) {
        return (VIEW) view;
    }
    @Provides @Singleton
    public PRESENTER presenter(PRESENTERIMPL presenter) {
        return (PRESENTER) presenter;
    }
}

package ru.bellski.mvpb;

import dagger.Module;
import dagger.Provides;
import ru.bellski.mvpb.mvp.*;
import ru.bellski.mvpb.navigation.token.ParameterTokenFormatter;
import ru.bellski.mvpb.navigation.token.PlaceTokenRegistry;
import ru.bellski.mvpb.proxy.PlaceManager;
import ru.bellski.mvpb.proxy.PlaceManagerImpl;
import ru.bellski.mvpb.proxy.VMVPEventBus;

import javax.inject.Singleton;

/**
 * Created by Aleksandr on 09.07.2016.
 */
@Module
public abstract class BootstrapModule<VIEW extends VMVPView, VIEWIMPL extends VMVPViewImpl, PRESENTER extends VMVPPresenter, PRESENTERIMPL extends VMVPPresenterComponent> extends VMVPModule<VIEW, VIEWIMPL, PRESENTER, PRESENTERIMPL> {

    @Singleton @Provides
    public ParameterTokenFormatter providesParameterTokenFormatter() {
        return new ParameterTokenFormatter();
    }

    @Singleton @Provides
    public PlaceTokenRegistry providesPlaceTokenRegistry() {
        return new PlaceTokenRegistry();
    }

    @Singleton @Provides
    public VMVPEventBus providesVmvpEventBus() {
        return new VMVPEventBus();
    }

    @Singleton @Provides
    public PlaceManager providesPlaceManager(PlaceManagerImpl placeManager){
        return placeManager;
    }
}

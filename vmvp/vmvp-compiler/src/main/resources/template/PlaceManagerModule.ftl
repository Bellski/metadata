<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.PlaceManagerModuleModel" -->

package org.vaadin.rise.place;

import com.vaadin.server.Page;
import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.place.token.PlaceTokenRegistry;
import org.vaadin.rise.place.token.RouteTokenFormatter;
import org.vaadin.rise.place.token.TokenFormatter;

import javax.inject.Singleton;


@Module
public class PlaceManagerModule {

    @Provides @Singleton
    DefaultPlaceManager providesPlaceManager(RouteTokenFormatter tokenFormatter, Page page) {
        return new DefaultPlaceManager(tokenFormatter, page, "${defaultPlaceNameToken}", "${errorPlaceNameToken}", "${unauthorizedPlaceNameToken}");
    }


    @Provides @Singleton
    RouteTokenFormatter providesTokenFormatter(PlaceTokenRegistry tokenRegistry) {
        return new RouteTokenFormatter(tokenRegistry);
    }
}

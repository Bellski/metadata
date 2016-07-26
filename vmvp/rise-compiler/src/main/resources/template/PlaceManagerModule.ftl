<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.PlaceManagerModuleModel" -->

package org.vaadin.rise.place;

import com.vaadin.server.Page;
import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.deprecated.token.PlaceTokenRegistry;
import org.vaadin.rise.place.deprecated.token.RouteTokenFormatter;


import javax.inject.Singleton;


@Module
public class PlaceManagerModule {

    @Provides @Singleton
    DefaultPlaceManager providesPlaceManager(RouteTokenFormatter tokenFormatter, Page page) {
        return new DefaultPlaceManager(tokenFormatter, page, "${defaultPlaceNameToken}", "${errorPlaceNameToken}", "${unauthorizedPlaceNameToken}", "${contextRoot}");
    }


    @Provides @Singleton
    RouteTokenFormatter providesTokenFormatter(PlaceTokenRegistry tokenRegistry) {
        return new RouteTokenFormatter(tokenRegistry);
    }
}

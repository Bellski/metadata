<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.PlaceManagerModuleModel" -->

package org.vaadin.rise.place;

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.BasePlaceManager;
import org.vaadin.rise.place.PageUriFragmentSource;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;

import javax.inject.Singleton;
import java.util.Map;

<#if hasPlaceBus()>
import ${placeBus.fullyQualifiedName};
</#if>

@Module
public class PlaceManagerModule {

    @Provides @Singleton
    DefaultPlaceManager providesPlaceManager(@Places Map<String, Place> placeMap,
                                             UriFragmentSource uriFragmentSource <#if hasPlaceBus()>,</#if>
                          <#if hasPlaceBus()>${placeBus.className} bus</#if>) {
        return new BasePlaceManager(
            placeMap,
            ApplicationNameTokens.nameTokens,
            bus,
            uriFragmentSource
        );
    }

    @Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
        return pageUriFragmentSource;
    }
}

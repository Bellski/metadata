<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.PlaceManagerModuleModel" -->

package ${packageName};

import dagger.Module;
import dagger.Provides;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PageUriFragmentSource;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.api.UriFragmentSource;
import org.vaadin.rise.error.ErrorManager;
import org.vaadin.rise.place.annotation.DefaultPlace;
import org.vaadin.rise.place.annotation.ErrorPlace;
import org.vaadin.rise.place.annotation.UnauthorizedPlace;

import javax.inject.Singleton;
import java.util.Map;

<#if hasPlaceBus()>
import ${placeBus.fullyQualifiedName};
</#if>

import ${errorManager.fullyQualifiedName};
import ${packageName}.NameTokenParts;

@Module
public class PlaceManagerModule {

    @Provides @Singleton
    PlaceManager providesPlaceManager(@Places Map<String, Place> placeMap, UriFragmentSource uriFragmentSource <#if defaultGateKeeper??>, ${defaultGateKeeper.className} gateKeeper </#if><#if hasPlaceBus()>,${placeBus.className} bus</#if>, ErrorManager errorManager) {
        return new DefaultPlaceManager(
            placeMap,
            NameTokenParts.parts, <#if hasPlaceBus()>bus,</#if>
            uriFragmentSource,
            <#if defaultGateKeeper??>gateKeeper, </#if>
            "${defaultPlaceNameToken! }",
            "${unauthorizedPlaceNameToken! }",
            errorManager
        );
    }

    @Provides @Singleton static UriFragmentSource uriFragmentSource(PageUriFragmentSource pageUriFragmentSource) {
        return pageUriFragmentSource;
    }

    @Provides @Singleton static ErrorManager errorManager(${errorManager.className} baseErrorManager) {
        return baseErrorManager;
    }

    @Provides @DefaultPlace @Singleton static String defaultPlace() {
        return "${defaultPlaceNameToken!}";
    }

    @Provides @ErrorPlace @Singleton static String errorPlace() {
        return "${errorPlaceNameToken!}";
    }

    @Provides @UnauthorizedPlace @Singleton static String unauthorizedPlace() {
        return "${unauthorizedPlaceNameToken!}";
    }
}

<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.light.model.PlacesModuleModel" -->
package ${packageName};

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.vaadin.rise.place.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;

<#list importList as importName>
import ${importName};
</#list>

import javax.inject.Singleton;

@Module
public class PlacesModule {

    <#list presenters as presenter>
    @Provides
    @Singleton
    @IntoMap
    @Places
    @StringKey("${presenter.placeName}")
    Place ${presenter.placeNameWithoutHashBang}PlaceValue(LazyPlacePresenter<${presenter.className}> lazyPlacePresenter) {
        return new ${presenter.placeImplementation.className}<>(
            lazyPlacePresenter,
            "${presenter.placeName}",
            "${presenter.placeName}",
            null,
            null
        );
    }
    </#list>
}
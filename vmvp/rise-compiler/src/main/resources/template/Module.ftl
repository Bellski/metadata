<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.ModuleModel" -->

package ${packageName};

import dagger.Module;
import dagger.Provides;


import javax.inject.Singleton;

<#list importList as importName>
import ${importName};
</#list>


<#if hasPlace()>
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import org.vaadin.rise.place.annotation.Places;
import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;
import org.vaadin.rise.place.PresenterPlace;
</#if>

<#if gateKeeper??>
import org.vaadin.rise.place.SecuredPresenterPlace;
import ${gateKeeper.fullyQualifiedName};
</#if>

@Module<#if hasIncludes()>(includes = {${joinedIncludes}})</#if>
public class ${className} extends ${extendsModule.className} {

    <#if hasConstructor()>
    public ${className}(${joinedConstructorParameters}) {
        super(${joinedConstructorParameterNames});
    }
    </#if>

    <#if hasPlace()>
        @Provides
        @Singleton
        @IntoMap
        @Places
        @StringKey("${daggerProvidesPlaceMethodModel.place}")
        Place placeValue(<#if gateKeeper??> ${gateKeeper.className} gateKeeper, </#if>LazyPlacePresenter<${daggerProvidesMethodPresenterModel.providesImpl.className}> lazyPlacePresenter) {
            return new <#if gateKeeper??>SecuredPresenterPlace<#else>PresenterPlace</#if><>(
                <#if gateKeeper??>gateKeeper, </#if>
                lazyPlacePresenter,
                "${daggerProvidesPlaceMethodModel.place}",
                "${daggerProvidesPlaceMethodModel.placeWithHiddenParameters}",
                <#if daggerProvidesPlaceMethodModel.paramNames??>
                new String[] {${daggerProvidesPlaceMethodModel.joinedParamNames()}},
                <#else>
                null,
                </#if>
                <#if daggerProvidesPlaceMethodModel.paramIndexes??>
                new int[] {${daggerProvidesPlaceMethodModel.joinedParamIndexes()}}
                <#else>
                null
                </#if>
            );
        }
    </#if>

    <#if hasSlots()>
    <#list slots as slot>
    @Provides @Singleton
    ${slot.providesImpl.fullClassName} ${slot.methodName}(${slot.providesImpl.className} slot) {
        return slot;
    }
    </#list>
    </#if>




    @Provides
    @Singleton
    ${daggerProvidesMethodViewModel.providesInterface.fullClassName} ${daggerProvidesMethodViewModel.methodName}(${daggerProvidesMethodViewModel.providesImpl.className} view) {
        return view;
    }

    @Provides
    @Singleton
    ${daggerProvidesMethodPresenterModel.providesInterface.fullClassName} ${daggerProvidesMethodPresenterModel.methodName}(${daggerProvidesMethodPresenterModel.providesImpl.className} presenter) {
        return presenter;
    }
}
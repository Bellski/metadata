<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.ModuleModel" -->

package ${packageName};

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

<#list importList as importName>
import ${importName};
</#list>

import org.vaadin.rise.deprecated.proxy.Proxy;

@Module<#if hasIncludes()>(includes = {${joinedIncludes}})</#if>
public class ${className} extends ${extendsModule.className} {

    <#if hasConstructor()>
    public ${className}(${joinedConstructorParameters}) {
        super(${joinedConstructorParameterNames});
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
    Proxy<${daggerProvidesProxyMethodModel.providesInterface.className}> ${daggerProvidesProxyMethodModel.methodName}(${daggerProvidesProxyMethodModel.providesImpl.className} proxy) {
        return proxy;
    }

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
<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.freemaker.ModuleModel" -->

package ${packageName};

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

<#list imports as importName>
import ${importName};
</#list>

@Module<#if hasIncludes()>(includes = {${joinedIncludes}})</#if>
public class ${className} extends ${extendsModule} {

    <#if hasSlots()>
    <#list slots as slot>
    @Provides @Singleton
    ${slot.apiName} provides${slot.implName}(${slot.implName} slot) {
        return slot;
    }
    </#list>
    </#if>

    @Provides @Singleton
    ${providesView.apiName} provides${providesView.implName}(${providesView.implName} view) {
        return view;
    }

    @Provides @Singleton
    ${providesPresenter.apiName} provides${providesPresenter.implName}(${providesPresenter.implName} presenter) {
        return presenter;
    }
}
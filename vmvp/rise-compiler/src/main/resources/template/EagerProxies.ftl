<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.EagerProxiesModel" -->

package ${packageName};


import javax.inject.Inject;
import javax.inject.Singleton;

<#list importList as importName>
import ${importName};
</#list>


@Singleton
public class EagerProxies {

    <#list proxyModels as daggerProvidesProxyMethodModel>
    @Inject
    ${daggerProvidesProxyMethodModel.className} ${daggerProvidesProxyMethodModel.className};
    </#list>

    @Inject
    public EagerProxies() {
    }
}

<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.ProxyModel" -->
package ${packageName};

import javax.inject.Inject;

<#list importList as importName>
import ${importName};
</#list>

<#if !isProxyPlace()>
import org.vaadin.rise.deprecated.proxy.BaseProxy;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;

public class ${className} extends BaseProxy<${proxyTarget.className}> {

    @Inject
    public ${className}(LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(lazyPresenter);
    }
}
</#if>

<#if isProxyPlace()>
import org.vaadin.rise.place.deprecated.DefaultPlaceManager_TODO_TODO;
import org.vaadin.rise.place.deprecated.PlaceImpl;
import org.vaadin.rise.deprecated.proxy.ProxyPlaceImpl;

<#if hasGateKeeper()>
import org.vaadin.rise.place.deprecated.PlaceWithGatekeeper;
import org.vaadin.rise.security.Gatekeeper;
</#if>

import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;

public class ${className} extends ProxyPlaceImpl<${proxyTarget.className}> {

    <#if hasGateKeeper()>
    @Inject
    public ${className}(${gateKeeper.className} gateKeeper, DefaultPlaceManager placeManagerTODO, LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(new PlaceWithGatekeeper("${placeName}", gateKeeper), placeManagerTODO, lazyPresenter);
    }

    <#else>
    @Inject
    public ${className}(DefaultPlaceManager placeManagerTODO, LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(new PlaceImpl("${placeName}"), placeManagerTODO, lazyPresenter);
    }
    </#if>
}
</#if>


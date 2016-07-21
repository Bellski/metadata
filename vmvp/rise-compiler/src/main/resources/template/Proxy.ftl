<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.ProxyModel" -->
package ${packageName};

import javax.inject.Inject;

<#list importList as importName>
import ${importName};
</#list>

<#if !isProxyPlace()>
import org.vaadin.rise.proxy.BaseProxy;
import org.vaadin.rise.proxy.LazyPresenter;

public class ${className} extends BaseProxy<${proxyTarget.className}> {

    @Inject
    public ${className}(LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(lazyPresenter);
    }
}
</#if>

<#if isProxyPlace()>
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceImpl;
import org.vaadin.rise.proxy.ProxyPlaceImpl;

<#if hasGateKeeper()>
import org.vaadin.rise.place.PlaceWithGatekeeper;
import org.vaadin.rise.security.Gatekeeper;
</#if>

import org.vaadin.rise.proxy.LazyPresenter;

public class ${className} extends ProxyPlaceImpl<${proxyTarget.className}> {

    <#if hasGateKeeper()>
    @Inject
    public ${className}(${gateKeeper.className} gateKeeper, DefaultPlaceManager placeManager, LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(new PlaceWithGatekeeper("${placeName}", gateKeeper), placeManager, lazyPresenter);
    }

    <#else>
    @Inject
    public ${className}(DefaultPlaceManager placeManager, LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(new PlaceImpl("${placeName}"), placeManager, lazyPresenter);
    }
    </#if>
}
</#if>


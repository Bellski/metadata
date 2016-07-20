<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.ProxyModel" -->
package ${packageName};

import dagger.Lazy;
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

import org.vaadin.rise.proxy.LazyPresenter;

public class ${className} extends ProxyPlaceImpl<${proxyTarget.className}> {

    @Inject
    public ${className}(DefaultPlaceManager placeManager, LazyPresenter<${proxyTarget.className}> lazyPresenter) {
        super(new PlaceImpl("${placeName}"), placeManager, lazyPresenter);
    }
}
</#if>


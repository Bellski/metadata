<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.BootstrapModel" -->

package ${packageName};

import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.place.DefaultPlaceManager;

<#list importList as importName>
import ${importName};
</#list>

import javax.inject.Inject;
import javax.inject.Singleton;

/**
* Created by oem on 7/12/16.
*/
@Singleton
public class Bootstrap {
    @Inject
    DefaultPlaceManager placeManager;

    @Inject
    ${eagerProxiesModel.className} eagerProxies;

    @Inject
    RootPresenter.RootSlot rootSlot;

    @Inject
    public Bootstrap() {
    }

    public void doBootstrap() {
        placeManager.revealDefaultPlace();
    }
}
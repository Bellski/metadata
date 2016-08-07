<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.AppInterfaceModel" -->
package ${packageName};

import org.vaadin.rise.core.RisePresenter;

<#if isPopup()>
import org.vaadin.rise.core.PopupView;
<#else>
import org.vaadin.rise.core.RiseView;
</#if>

<#assign view>
    <#if isPopup()>
PopupView<Presenter>
    <#else>
RiseView<Presenter>
    </#if>
</#assign>

public interface ${name} {
    interface View extends ${view} {}

    interface Presenter extends RisePresenter<View> {}
}
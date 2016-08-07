<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.RisePresenterModel" -->
package ${packageName};

<#assign viewApi = "${apiName}.View">
<#assign isRisePresenterImpl = isPresenterImpl()>
<#assign hasSlot = slot??>

import org.vaadin.rise.core.annotation.Presenter;
<#if isRisePresenterImpl>
import org.vaadin.rise.core.RisePresenterImpl;
<#else>
import org.vaadin.rise.core.RisePresenterComponent;
</#if>

<#if hasSlot>
import ${slot.psiClassModel.fqn};
</#if>

import javax.inject.Inject;

<#assign presenter>
    <#if isRisePresenterImpl>
RisePresenterImpl<${viewApi}>
    <#else>
RisePresenterComponent<${viewApi}>
    </#if>
</#assign>

@Presenter <#if nameToken??>(placeName = "${nameToken}")</#if>
public class ${name} extends ${presenter} implements ${apiName}.Presenter {

<#if hasSlot>
    @Inject
    public ${name}(${viewApi} view, ${slot.psiClassModel.name} slot) {
        super(view, slot);
    }
<#else>
    @Inject
    public ${name}(${viewApi} view) {
        super(view);
    }
</#if>
}


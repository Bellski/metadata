<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.RisePresenterModel" -->

<#assign viewApi = "${apiName}.View">
<#assign isRisePresenterImpl = isPresenterImpl()>
<#assign hasSlot = slot??>

package ${packageName};

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

@Presenter <#if nameToken??>(placeName = "${nameToken}")</#if>
public class ${name} extends
<#if isRisePresenterImpl>
    RisePresenterImpl<${viewApi}>
<#else>
    RisePresenterComponent<${viewApi}>
</#if> implements ${apiName}.Presenter {

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


<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.RiseViewModel" -->
package ${packageName};

import com.vaadin.ui.UI;

<#if isPopup()>
import org.vaadin.rise.core.PopupViewImpl;
<#else>
import org.vaadin.rise.core.RiseViewImpl;
</#if>

import javax.inject.Inject;

public class ${name} extends
<#if isPopup()>
PopupViewImpl<${apiName}.Presenter>
<#else>
RiseViewImpl<${apiName}.Presenter>
</#if>
implements ${apiName}.View {

    @Inject
    public ${name}(UI ui) {
        super(ui);
    }
}
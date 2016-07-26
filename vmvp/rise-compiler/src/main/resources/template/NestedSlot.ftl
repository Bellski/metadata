<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.model.NestedSlotModel" -->
package ${packageName};

<#list importList as importName>
import ${importName};
</#list>

import dagger.Lazy;
import javax.inject.Inject;

import org.vaadin.rise.deprecated.proxy.slot.NestedSlot;


public class ${className} extends NestedSlot<${slotOwner.className}> implements ${extendsSlot.fullClassName} {

    @Inject
    public ${className}(Lazy<${slotOwner.className}> presenter) {
        super(presenter);
    }
}

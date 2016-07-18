<#-- @ftlvariable name="" type="org.vaadin.rise.codegen.freemaker.NestedSlotModel" -->
package ${originalFQN.packageName};

import ${presenterFQN.getFQN()};
import ${implementsSlotFQN.getFQN()};

import dagger.Lazy;
import javax.inject.Inject;

import org.vaadin.rise.proxy.slot.NestedSlot;


public class ${className} extends NestedSlot<${presenterFQN.className}> implements ${implementsSlotFQN.className} {

    @Inject
    public ${className}(Lazy<${presenterFQN.className}> presenter) {
        super(presenter);
    }
}

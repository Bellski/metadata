<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.SlotImplementationModel" -->

public static class ${name} extends ${slotType.getName()}<${presenterImpl.name}> implements ${implementsSlot.name} {

    @Inject
    public ${name}(Lazy<${presenterImpl.name}> presenter) {
        super(presenter);
    }
}



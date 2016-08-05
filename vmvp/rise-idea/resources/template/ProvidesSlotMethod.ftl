<#-- @ftlvariable name="" type="org.vaadin.rise.plugin.idea.model.SlotImplementationModel" -->

@Provides
@Singleton
${implementsSlot.name} provides${name}(${name} slot) {
    return slot;
}
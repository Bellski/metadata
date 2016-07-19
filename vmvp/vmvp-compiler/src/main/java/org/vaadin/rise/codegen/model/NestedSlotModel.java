package org.vaadin.rise.codegen.model;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class NestedSlotModel extends JFOModel {
    private FqnHolder slotOwner;
    private final FqnHolder extendsSlot;

    public NestedSlotModel(String className, String packageName, FqnHolder extendsSlot) {
        super(className, className, packageName);
        this.extendsSlot = extendsSlot;
    }

    public FqnHolder getSlotOwner() {
        return slotOwner;
    }

    public void setSlotOwner(FqnHolder slotOwner) {
        this.slotOwner = slotOwner;
        addImport(slotOwner);
    }

    public FqnHolder getExtendsSlot() {
        return extendsSlot;
    }
}

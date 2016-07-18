package org.vaadin.rise.codegen.freemaker;

/**
 * Created by Aleksandr on 17.07.2016.
 */
public class NestedSlotModel {
    private final FQN nestedSlotFQN;
    private final FQN originalFQN;
    private FQN presenterFQN;
    private FQN implementsSlotFQN;

    public NestedSlotModel(FQN originalFQN, FQN presenterFQN) {
        this.nestedSlotFQN = FQN.create("NestedSlot" + originalFQN.getClassName().replaceAll("\\.", ""), originalFQN.getPackageName());
        this.originalFQN = originalFQN;
        this.implementsSlotFQN = originalFQN;
        this.presenterFQN = presenterFQN;
    }


    public FQN getOriginalFQN() {
        return originalFQN;
    }

    public FQN getPresenterFQN() {
        return presenterFQN;
    }

    public FQN getImplementsSlotFQN() {
        return implementsSlotFQN;
    }

    public String getClassName() {
        return nestedSlotFQN.getClassName();
    }

    public String getFQNClassName() {
        return originalFQN.getPackageName() + "." + getClassName();
    }

    public FQN getNestedSlotFQN() {
        return nestedSlotFQN;
    }
}

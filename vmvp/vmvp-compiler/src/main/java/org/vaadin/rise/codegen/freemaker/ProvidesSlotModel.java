package org.vaadin.rise.codegen.freemaker;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ProvidesSlotModel {
    private FQN nestedSlot;
    private FQN api;
    private FQN impl;

    private String apiName;
    private String implName;

    public ProvidesSlotModel(FQN api, FQN impl) {
        this.api = api;
        this.impl = impl;

        this.apiName = api.getClassName();
        this.implName = impl.getClassName();
    }

    public ProvidesSlotModel(NestedSlotModel slotModel) {
        this.api = slotModel.getImplementsSlotFQN();
        this.impl = slotModel.getOriginalFQN();

        this.apiName = slotModel.getImplementsSlotFQN().getClassName();
        this.implName = slotModel.getClassName();

        this.nestedSlot = slotModel.getNestedSlotFQN();
    }

    public FQN getFQNApi() {
        return api;
    }

    public FQN getFQNImpl() {
        return impl;
    }

    public String getApiName() {
        return apiName;
    }

    public String getImplName() {
        return implName.replaceAll("\\.", "");
    }

    public FQN getNestedSlot() {
        return nestedSlot;
    }
}

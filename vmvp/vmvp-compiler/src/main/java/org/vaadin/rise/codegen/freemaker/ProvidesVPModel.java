package org.vaadin.rise.codegen.freemaker;

/**
 * Created by Aleksandr on 16.07.2016.
 */
public class ProvidesVPModel {
    private FQN api;
    private FQN impl;

    private String apiName;
    private String implName;

    public ProvidesVPModel(FQN api, FQN impl) {
        this.api = api;
        this.impl = impl;

        this.apiName = api.getClassName();
        this.implName = impl.getClassName();
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
        return implName;
    }
}

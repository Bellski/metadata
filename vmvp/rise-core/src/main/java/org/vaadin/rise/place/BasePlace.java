package org.vaadin.rise.place;

import org.vaadin.rise.place.api.Place;
import org.vaadin.rise.security.Gatekeeper;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public abstract class BasePlace implements Place {
    protected String nameToken;
    protected String nameTokenWithHiddenParams;

    protected String[] paramNames;
    protected int[] paramIndexes;

    public BasePlace() {
    }

    public BasePlace(String nameToken, String nameTokenWithHiddenParams, String[] paramNames, int[] paramIndexes) {
        this.nameToken = nameToken;
        this.nameTokenWithHiddenParams = nameTokenWithHiddenParams;
        this.paramNames = paramNames;
        this.paramIndexes = paramIndexes;
    }

    @Override
    public String getNameToken() {
        return nameToken;
    }

    @Override
    public String[] getParamNames() {
        return paramNames;
    }

    @Override
    public int[] getParamIndexes() {
        return paramIndexes;
    }

    @Override
    public boolean hasParameters() {
        return paramNames != null;
    }

    @Override
    public boolean canReveal() {
        return true;
    }

    @Override
    public String getFallBackNamePlace() {
        return null;
    }

    @Override
    public void replaceGateKeeper(Gatekeeper gatekeeper) {

    }

    @Override
    public String toString() {
        return nameToken;
    }

}


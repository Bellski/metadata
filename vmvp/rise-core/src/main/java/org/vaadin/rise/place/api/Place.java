package org.vaadin.rise.place.api;

import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public interface Place extends ComparablePlace {
    String[] getParamNames();

    int[] getParamIndexes();

    boolean hasParameters();

    boolean canReveal();

    RisePresenterImpl<?> reveal();

    String getNameToken();
}

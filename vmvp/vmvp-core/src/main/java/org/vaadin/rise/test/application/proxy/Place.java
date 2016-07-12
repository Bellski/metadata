package org.vaadin.rise.test.application.proxy;


import org.vaadin.rise.test.application.navigation.PlaceRequest;

/**
 * Created by Aleksandr on 10.07.2016.
 */
public interface Place {
    boolean canReveal();

    @Override
    boolean equals(Object o);

    String getNameToken();

    @Override
    int hashCode();

    boolean matchesRequest(PlaceRequest request);

    @Override
    String toString();
}

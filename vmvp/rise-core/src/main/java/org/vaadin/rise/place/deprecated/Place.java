package org.vaadin.rise.place.deprecated;

import org.vaadin.rise.place.CanReveal;

public interface Place {

    CanReveal canReveal();

    @Override
    boolean equals(Object o);

    String getNameToken();

    @Override
    int hashCode();

    boolean matchesRequest(PlaceRequest request);

    @Override
    String toString();

}
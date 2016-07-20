package org.vaadin.rise.place;

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
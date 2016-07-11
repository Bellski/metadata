package ru.bellski.mvpb.proxy;

import ru.bellski.mvpb.navigation.PlaceRequest;

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

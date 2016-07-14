package org.vaadin.rise.place;

import java.util.Arrays;

public class PlaceImpl implements Place {
    private final String[] nameTokens;

    public PlaceImpl(String... nameTokens) {
        this.nameTokens = nameTokens;
    }

    @Override
    public boolean canReveal() {
        return true;
    }

    @Override
    public final boolean equals(Object o) {
        if (o instanceof PlaceImpl) {
            PlaceImpl place = (PlaceImpl) o;
            return Arrays.equals(nameTokens, place.nameTokens);
        }
        if (o instanceof Place) {
            Place place = (Place) o;
            for (String nameToken : nameTokens) {
                if (nameToken.equals(place.getNameToken())) {
                    return true;
                }
            }
            return getNameToken().equals(place.getNameToken());
        }
        return false;
    }

    @Override
    public String getNameToken() {
        return nameTokens[0];
    }

    public String[] getNameTokens() {
        return nameTokens;
    }

    @Override
    public final int hashCode() {
        return 17 * Arrays.hashCode(nameTokens);
    }

    @Override
    public final boolean matchesRequest(PlaceRequest request) {
        for (String nameToken : nameTokens) {
            if (request.matchesNameToken(nameToken)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public final String toString() {
        return getNameToken();
    }
}
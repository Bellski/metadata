package org.vaadin.rise.place.deprecated.token;

/**
 * Created by Aleksandr on 22.07.2016.
 */
public class FragmentUri implements BaseTokenPlace {
    private final String uri;
    private final String[] uriParts;

    public FragmentUri(String uri) {
        this.uri = uri;
        this.uriParts = uri.split("/");
    }

    public String getUri() {
        return uri;
    }

    public String[] getUriParts() {
        return uriParts;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TokenPlace)) {
            return false;
        }

        final TokenPlace tokenPlace = (TokenPlace) o;

        final String[] tokenPlaces = tokenPlace.getTokenPlaces();

        if (uriParts.length < tokenPlaces.length) return false;


        final String[] tokenParts = tokenPlace.getFragmentUriParts();

        if (tokenParts.length != uriParts.length) return false;


        final int[] tokenIndexes = tokenPlace.getTokenIndexes();

        for (int i = 0; i < tokenIndexes.length; i++) {
            if (!uriParts[tokenIndexes[i]].equals(tokenParts[tokenIndexes[i]])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        if (uriParts.length == 1) {
            return (uriParts[0]).hashCode();
        }

        return (uriParts[0] + uriParts[1]).hashCode();
    }
}

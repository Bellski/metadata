package org.vaadin.rise.place.deprecated.token;

import java.util.Arrays;

/**
 * Created by Aleksandr on 22.07.2016.
 */
public class TokenPlace implements BaseTokenPlace {
    private final String originalTokenPlace;
    private String[] fragmentUriParts;

    private String[] tokenPlaces;
    private String[] params;
    private int[] tokenIndexes;


    public TokenPlace(String originalTokenPlace) {
        this.originalTokenPlace = originalTokenPlace;
        this.fragmentUriParts = originalTokenPlace.split("/");

        for (int i = 0; i < fragmentUriParts.length; i++) {
            if (fragmentUriParts[i].charAt(0) != '{') {
                if (tokenPlaces == null) {
                    tokenPlaces = new String[] {fragmentUriParts[i]};

                    if (tokenIndexes == null) {
                        tokenIndexes = new int[] {i};
                    }

                } else {
                    tokenPlaces = Arrays.copyOf(tokenPlaces, tokenPlaces.length + 1);
                    tokenPlaces[tokenPlaces.length -1] = fragmentUriParts[i];

                    tokenIndexes = Arrays.copyOf(tokenIndexes, tokenIndexes.length + 1);
                }
            }
        }
    }


    public String getOriginalTokenPlace() {
        return originalTokenPlace;
    }

    public String[] getFragmentUriParts() {
        return fragmentUriParts;
    }

    public String[] getTokenPlaces() {
        return tokenPlaces;
    }

    public String[] getParams() {
        return params;
    }

    public int[] getTokenIndexes() {
        return tokenIndexes;
    }

    @Override
    public int hashCode() {
        return (fragmentUriParts[0] + fragmentUriParts[1]).hashCode();
    }
}

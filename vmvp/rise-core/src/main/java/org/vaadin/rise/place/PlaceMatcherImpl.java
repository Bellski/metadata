package org.vaadin.rise.place;

import org.vaadin.rise.place.api.PlaceMatcher;

import java.util.Arrays;

/**
 * Created by Aleksandr on 22.07.2016.
 */
public class PlaceMatcherImpl implements PlaceMatcher {
    private String originalTokenPlace;
    private String[] fragmentUriParts;

    private String[] tokenPlaces;
    private int[] tokenIndexes;

    private String[] paramNames;
    private int[] paramsIndexes;

    public PlaceMatcherImpl(String originalTokenPlace) {
        this.originalTokenPlace = originalTokenPlace;
        this.fragmentUriParts = originalTokenPlace.split("/");

        for (int i = 0; i < fragmentUriParts.length; i++) {
            if (fragmentUriParts[i].charAt(0) != '{') {
                if (tokenPlaces == null) {
                    tokenPlaces = new String[]{fragmentUriParts[i]};
                    tokenIndexes = new int[]{i};
                } else {
                    tokenPlaces = Arrays.copyOf(tokenPlaces, tokenPlaces.length + 1);
                    tokenPlaces[tokenPlaces.length - 1] = fragmentUriParts[i];

                    tokenIndexes = Arrays.copyOf(tokenIndexes, tokenIndexes.length + 1);
                    tokenIndexes[tokenIndexes.length - 1] = i;
                }
            } else if (fragmentUriParts[i].charAt(0) == '{') {
                if (paramNames == null) {
                    paramNames = new String[] {fragmentUriParts[i].substring(1, fragmentUriParts[i].length() - 1)};
                    paramsIndexes = new int[]{i};
                } else {
                    paramNames = Arrays.copyOf(paramNames, paramNames.length +1 );
                    paramNames[paramNames.length -1] = fragmentUriParts[i].substring(1, fragmentUriParts[i].length() - 1);

                    paramsIndexes = Arrays.copyOf(paramsIndexes, paramsIndexes.length + 1);
                    paramsIndexes[paramsIndexes.length - 1] = i;
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

    public int[] getTokenIndexes() {
        return tokenIndexes;
    }

    public String[] getParamNames() {
        return paramNames;
    }

    public int[] getParamsIndexes() {
        return paramsIndexes;
    }

    public boolean hasParams() {
        return paramNames != null;
    }

    @Override
    public int hashCode() {
        return (fragmentUriParts[0] + fragmentUriParts[1]).hashCode();
    }

    @Override
    public String toString() {
        return originalTokenPlace;
    }
}

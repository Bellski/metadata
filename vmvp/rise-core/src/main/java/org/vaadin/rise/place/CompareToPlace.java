package org.vaadin.rise.place;

import org.vaadin.rise.place.api.ComparablePlace;
import org.vaadin.rise.place.api.Place;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by Aleksandr on 22.07.2016.
 */
public class CompareToPlace implements ComparablePlace {
    private final String uriFragment;
    private final String[] uriFragmentParts;

    private String[] nameTokens;
    private int[] nameTokensIndexes;

    private int[] paramIndexes;

    public CompareToPlace(String uriFragment, Map<String, String> nameTokenMap) {
        this.uriFragment = uriFragment;
        this.uriFragmentParts = uriFragment.split("/");

        for (int i = 0; i < this.uriFragmentParts.length; i++) {
            final String nameToken = nameTokenMap.get(uriFragmentParts[i]);

            if (nameToken != null) {
                if (nameTokens == null) {
                    nameTokens = new String[] {nameToken};
                    nameTokensIndexes = new int[] {i};
                } else {
                    nameTokens = Arrays.copyOf(nameTokens, nameTokens.length + 1);
                    nameTokens[nameTokens.length - 1] = nameToken;

                    nameTokensIndexes = Arrays.copyOf(nameTokensIndexes, nameTokensIndexes.length + 1);
                    nameTokensIndexes[nameTokensIndexes.length - 1] = i;
                }
            } else {
                if (paramIndexes == null) {
                    paramIndexes = new int[] {i};
                } else {
                    paramIndexes = Arrays.copyOf(paramIndexes, paramIndexes.length + 1);
                    paramIndexes[paramIndexes.length - 1] = i;
                }
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        boolean equals = false;

        if (o instanceof Place) {
            final Place place = (Place) o;

            if (Arrays.equals(nameTokens, place.getNameTokens())) {
                if (Arrays.equals(paramIndexes, place.getParamIndexes())) {
                    equals = true;
                }
            }
        }

        return equals;
    }

    @Override
    public int hashCode() {
        return 17 + Arrays.hashCode(nameTokens) + (paramIndexes == null ? 0 : paramIndexes.length);
    }

    @Override
    public String toString() {
        return uriFragment;
    }
}

package org.vaadin.rise.place;

import org.vaadin.rise.place.api.Place;

import java.util.Arrays;

/**
 * Created by Aleksandr on 25.07.2016.
 */
public abstract class BasePlace implements Place {
    protected String nameToken;
    protected String[] nameTokenParts;

    protected String[] nameTokens;
    protected int[] nameTokensIndexes;

    protected String[] paramNames;
    protected int[] paramIndexes;

    public BasePlace() {
    }

    public BasePlace(String nameToken, String[] nameTokenParts, String[] nameTokens, int[] nameTokensIndexes, String[] paramNames, int[] paramIndexes) {
        this.nameToken = nameToken;
        this.nameTokenParts = nameTokenParts;
        this.nameTokens = nameTokens;
        this.nameTokensIndexes = nameTokensIndexes;
        this.paramNames = paramNames;
        this.paramIndexes = paramIndexes;
    }

    public BasePlace(PlaceDataBuilder placeDataBuilder) {
        nameToken = placeDataBuilder.nameToken;
        nameTokenParts = placeDataBuilder.nameTokenParts;

        nameTokens = placeDataBuilder.nameTokens;
        nameTokensIndexes = placeDataBuilder.nameTokensIndexes;

        paramNames = placeDataBuilder.paramNames;
        paramIndexes = placeDataBuilder.paramIndexes;
    }

    @Override
    public String getNameToken() {
        return nameToken;
    }

    @Override
    public String[] getNameTokenParts() {
        return nameTokenParts;
    }

    @Override
    public String[] getNameTokens() {
        return nameTokens;
    }

    @Override
    public int[] getNameTokenIndexes() {
        return nameTokensIndexes;
    }

    @Override
    public String[] getParamNames() {
        return paramNames;
    }

    @Override
    public int[] getParamIndexes() {
        return paramIndexes;
    }

    @Override
    public boolean hasParameters() {
        return paramNames != null;
    }

    @Override
    public boolean canReveal() {
        return false;
    }


    @Override
    public int hashCode() {
        return 17 + Arrays.hashCode(nameTokens) + (paramIndexes == null ? 0 : paramIndexes.length);
    }

    @Override
    public String toString() {
        return nameToken;
    }

    public static class PlaceDataBuilder {
        String nameToken;
        String[] nameTokenParts;

        String[] nameTokens;
        int[] nameTokensIndexes;

        String[] paramNames;
        int[] paramIndexes;

        private PlaceDataBuilder(String nameToken) {
            this.nameToken = nameToken;
            this.nameTokenParts = nameToken.split("/");

            for (int i = 0; i < nameTokenParts.length; i++) {
                if (nameTokenParts[i].charAt(0) != '{') {
                    if (nameTokens == null) {
                        nameTokens = new String[]{nameTokenParts[i]};
                        nameTokensIndexes = new int[]{i};
                    } else {
                        nameTokens = Arrays.copyOf(nameTokens, nameTokens.length + 1);
                        nameTokens[nameTokens.length - 1] = nameTokenParts[i];

                        nameTokensIndexes = Arrays.copyOf(nameTokensIndexes, nameTokensIndexes.length + 1);
                        nameTokensIndexes[nameTokensIndexes.length - 1] = i;
                    }
                } else if (nameTokenParts[i].charAt(0) == '{') {
                    if (paramNames == null) {
                        paramNames = new String[] {nameTokenParts[i].substring(1, nameTokenParts[i].length() - 1)};
                        paramIndexes = new int[]{i};
                    } else {
                        paramNames = Arrays.copyOf(paramNames, paramNames.length +1 );
                        paramNames[paramNames.length -1] = nameTokenParts[i].substring(1, nameTokenParts[i].length() - 1);

                        paramIndexes = Arrays.copyOf(paramIndexes, paramIndexes.length + 1);
                        paramIndexes[paramIndexes.length - 1] = i;
                    }
                }
            }
        }

        public static PlaceDataBuilder build(String nameToken) {
            return new PlaceDataBuilder(nameToken);
        }
    }
}


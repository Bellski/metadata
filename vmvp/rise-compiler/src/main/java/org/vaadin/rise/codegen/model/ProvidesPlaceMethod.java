package org.vaadin.rise.codegen.model;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by oem on 7/28/16.
 */
public class ProvidesPlaceMethod {
	private final String place;
	private String placeWithHiddenParameters;
	private String[] paramNames = null;
	private int[] paramIndexes = null;

	private FqnHolder lazyPresenter;

	public ProvidesPlaceMethod(String place, FqnHolder lazyPresenter) {
		this.place = place;
		this.lazyPresenter = lazyPresenter;

		final String[] placeParts = place.split("/");
		final StringJoiner placeWithHiddenParametersJoiner = new StringJoiner("/");

		for (int i = 0; i < placeParts.length; i++) {
			final  String placePart = placeParts[i];

			if (placePart.startsWith("{")) {
				placeWithHiddenParametersJoiner.add("?");

				if (paramNames == null) {
					paramNames = new String[] {placePart};
					paramIndexes = new int[] {i};
				} else {
					paramNames = Arrays.copyOf(paramNames, paramNames.length + 1);
					paramNames[paramNames.length - 1] = placePart;

					paramIndexes = Arrays.copyOf(paramIndexes, paramIndexes.length + 1);
					paramIndexes[paramIndexes.length - 1] = i;
				}
			} else {
				placeWithHiddenParametersJoiner.add(placePart);
			}
		}

		placeWithHiddenParameters = placeWithHiddenParametersJoiner.toString();
	}

	public String getPlace() {
		return place;
	}

	public String getPlaceWithHiddenParameters() {
		return placeWithHiddenParameters;
	}

	public String[] getParamNames() {
		return paramNames;
	}

	public String joinedParamNames() {
		final StringJoiner stringJoiner = new StringJoiner(",");

		for (String paramName : paramNames) {
			stringJoiner.add("\"" + paramName.substring(1, paramName.length() -1) + "\"");
		}

		return stringJoiner.toString();
	}

	public int[] getParamIndexes() {
		return paramIndexes;
	}

	public String joinedParamIndexes() {
		final StringJoiner stringJoiner = new StringJoiner(",");

		for (int paramIndex : paramIndexes) {
			stringJoiner.add(String.valueOf(paramIndex));
		}

		return stringJoiner.toString();
	}
}

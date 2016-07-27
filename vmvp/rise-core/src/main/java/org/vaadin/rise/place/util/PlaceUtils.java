package org.vaadin.rise.place.util;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by oem on 7/27/16.
 */
public class PlaceUtils {
	public static String convertToPlaceString(String uriFragment, Set<String> places) {
		final StringJoiner placeStringJoiner = new StringJoiner("/");

		for (String part : uriFragment.split("/")) {
			if (places.contains(part)) {
				placeStringJoiner.add(part);
			} else {
				placeStringJoiner.add("?");
			}
		}

		return placeStringJoiner.toString();
	}
}

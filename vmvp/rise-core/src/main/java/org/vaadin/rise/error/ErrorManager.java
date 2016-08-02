package org.vaadin.rise.error;

import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.PlaceRequest;

/**
 * Created by oem on 8/2/16.
 */
public interface ErrorManager {
	void onErrorOccurrence(PlaceRequest placeRequest, Exception e);
	void onErrorOccurrence();
	void onErrorOccurrence(String message);
	void onPlaceNotFound(String namePlace);

	void setPlaceManager(PlaceManager placeManager);
}

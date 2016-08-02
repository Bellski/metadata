package org.vaadin.rise.error;

import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.ui.UI;
import org.vaadin.rise.place.annotation.ErrorPlace;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.deprecated.PlaceRequest;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by oem on 8/2/16.
 */
public class BaseErrorManager implements ErrorManager, ErrorHandler {
	private PlaceManager placeManager;

	private final PlaceRequest errorPlaceRequest;

	public static final Logger logger = Logger.getLogger("BaseErrorManager");

	@Inject
	public BaseErrorManager(@ErrorPlace String errorPlaceNameToken, UI ui) {
		errorPlaceRequest = new PlaceRequest.Builder().nameToken(errorPlaceNameToken).build();
		ui.setErrorHandler(this);
	}

	@Override
	public void onErrorOccurrence(PlaceRequest placeRequest, Exception e) {
		placeManager.revealPlace(errorPlaceRequest, false);
		log(placeRequest.getNameToken(), e);
	}

	@Override
	public void onErrorOccurrence() {
		placeManager.revealPlace(errorPlaceRequest, false);
	}

	@Override
	public void onErrorOccurrence(String message) {
		placeManager.revealPlace(errorPlaceRequest, false);
	}

	@Override
	public void onPlaceNotFound(String namePlace) {
		placeManager.revealPlace(errorPlaceRequest, false);
	}

	@Override
	public void setPlaceManager(PlaceManager placeManager) {
		this.placeManager = placeManager;
	}

	@Override
	public void error(ErrorEvent event) {
		placeManager.revealPlace(errorPlaceRequest, false);
		log(null, event.getThrowable());
	}

	protected void log(String nameToken, Throwable e) {
		logger.log(Level.SEVERE, nameToken == null ? "" : nameToken, e);
	}
}

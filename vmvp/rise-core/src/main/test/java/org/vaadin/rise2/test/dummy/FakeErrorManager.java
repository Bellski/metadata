package org.vaadin.rise2.test.dummy;

import com.vaadin.server.ErrorEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.vaadin.rise.error.BaseErrorManager;
import org.vaadin.rise.place.api.PlaceManager;
import org.vaadin.rise.place.deprecated.PlaceRequest;

/**
 * Created by oem on 8/2/16.
 */
public class FakeErrorManager extends BaseErrorManager {
	public FakeErrorManager() {
		super(null, new UI() {
			@Override
			protected void init(VaadinRequest request) {

			}
		});
	}

	@Override
	public void onErrorOccurrence(PlaceRequest placeRequest, Exception e) {

	}

	@Override
	public void onErrorOccurrence() {

	}

	@Override
	public void onErrorOccurrence(String message) {

	}

	@Override
	public void onPlaceNotFound(String namePlace) {

	}

	@Override
	public void setPlaceManager(PlaceManager placeManager) {

	}

	@Override
	public void error(ErrorEvent event) {

	}

	@Override
	protected void log(String nameToken, Throwable e) {

	}
}

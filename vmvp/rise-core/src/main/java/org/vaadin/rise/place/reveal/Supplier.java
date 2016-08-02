package org.vaadin.rise.place.reveal;

import org.vaadin.rise.core.RisePresenterImpl;

/**
 * Created by oem on 8/2/16.
 */
public interface Supplier {
	void onFailure(RevealException e);
	void onFailure();
	void onSuccess(RisePresenterImpl<?> presenter);
}

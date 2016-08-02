package org.app.popup;

import org.vaadin.rise.core.RisePresenterComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 8/2/16.
 */
@Singleton
public class PopupPresenterComponent extends RisePresenterComponent<PopUp.View> implements PopUp.Presenter {
	@Inject
	protected PopupPresenterComponent(PopUp.View view) {
		super(view);
	}
}

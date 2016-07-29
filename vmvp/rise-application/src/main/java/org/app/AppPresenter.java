package org.app;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;

import javax.inject.Inject;

/**
 * Created by oem on 7/29/16.
 */
@Presenter
public class AppPresenter extends RisePresenterImpl<App.View> implements App.Presenter {

	@Inject
	protected AppPresenter(App.View view) {
		super(view);
	}
}

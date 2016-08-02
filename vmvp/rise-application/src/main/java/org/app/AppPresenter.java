package org.app;

import org.app.popup.PopupPresenterComponent;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.place.reveal.Supplier;

import javax.inject.Inject;

/**
 * Created by oem on 7/29/16.
 */
@Presenter
public class AppPresenter extends RisePresenterImpl<App.View> implements App.Presenter {

	private final PopupPresenterComponent popupPresenterComponent;

	@Inject
	protected AppPresenter(App.View view, PopupPresenterComponent popupPresenterComponent) {
		super(view);

		this.popupPresenterComponent = popupPresenterComponent;

		addToPopupSlot(popupPresenterComponent);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request, Supplier<?> supplier) {
		popupPresenterComponent.getView().show();
	}

	@Override
	protected void onHide() {
		popupPresenterComponent.getView().hide();
	}
}

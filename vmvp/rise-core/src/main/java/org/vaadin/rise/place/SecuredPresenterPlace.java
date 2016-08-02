package org.vaadin.rise.place;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.security.Gatekeeper;

/**
 * Created by oem on 7/29/16.
 */
public class SecuredPresenterPlace<Presenter_ extends RisePresenterImpl<?>> extends PresenterPlace<Presenter_> {
	private Gatekeeper gatekeeper;

	public SecuredPresenterPlace(Gatekeeper gatekeeper,
								 LazyPlacePresenter<Presenter_> lazyPresenter,
								 String nameToken,
								 String nameTokenWithHiddenParams,
								 String[] paramNames,
								 int[] paramIndexes) {

		super(
			lazyPresenter,
			nameToken,
			nameTokenWithHiddenParams,
			paramNames,
			paramIndexes
		);

		this.gatekeeper = gatekeeper;
	}

	@Override
	public void replaceGateKeeper(Gatekeeper gatekeeper) {
		this.gatekeeper = gatekeeper;
	}

	@Override
	public boolean canReveal() {
		return gatekeeper.canReveal();
	}

	@Override
	public String getFallBackNamePlace() {
		return gatekeeper.fallBackNamePlace();
	}
}

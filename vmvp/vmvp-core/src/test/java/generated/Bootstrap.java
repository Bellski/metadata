package generated;

import generated.org.vaadin.rise.test.application.RiseCas1PresenterStandardProxy;
import generated.org.vaadin.rise.test.application.claimlist.RiseCas1ClaimListPresenterPlaceProxy;
import org.vaadin.rise.place.DefaultPlaceManager;
import org.vaadin.rise.place.PlaceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class Bootstrap {

	RiseCas1PresenterStandardProxy riseCas1PresenterStandardProxy;

	RiseCas1ClaimListPresenterPlaceProxy riseCas1ClaimListPresenterPlaceProxy;

	@Inject
	public Bootstrap(DefaultPlaceManager placeManager, RiseCas1PresenterStandardProxy riseCas1PresenterStandardProxy, RiseCas1ClaimListPresenterPlaceProxy riseCas1ClaimListPresenterPlaceProxy) {
		placeManager.revealDefaultPlace();
	}
}

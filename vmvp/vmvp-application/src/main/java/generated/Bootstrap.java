package generated;

import generated.org.vaadin.rise.test.application.RiseCas1PresenterStandardProxy;
import generated.org.vaadin.rise.test.application.claiminfo.RiseCas1ClaimInfoPresenterPlaceProxy;
import generated.org.vaadin.rise.test.application.claimlist.RiseCas1ClaimListPresenterPlaceProxy;
import generated.org.vaadin.rise.test.application.error.RisErrorPresenterPlaceProxy;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.place.DefaultPlaceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class Bootstrap {

	@Inject
	public Bootstrap(DefaultPlaceManager placeManager,
					 RiseCas1PresenterStandardProxy riseCas1PresenterStandardProxy,
					 RiseCas1ClaimListPresenterPlaceProxy riseCas1ClaimListPresenterPlaceProxy,
					 RiseCas1ClaimInfoPresenterPlaceProxy riseCas1ClaimInfoPresenterPlaceProxy,
					 RisErrorPresenterPlaceProxy risErrorPresenterPlaceProxy,
					 RootPresenter.RootSlot rootSlot) {

		placeManager.revealDefaultPlace();
	}
}

package generated.rise;

import generated.org.vaadin.rise.test.application.RiseCas1PresenterStandardProxy;
import generated.org.vaadin.rise.test.application.claiminfo.RiseCas1ClaimInfoPresenterPlaceProxy;
import generated.org.vaadin.rise.test.application.claimlist.RiseCas1ClaimListPresenterPlaceProxy;
import generated.org.vaadin.rise.test.application.error.RisErrorPresenterPlaceProxy;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/15/16.
 */
@Singleton
public class EagerProxies {
	@Inject
	RiseCas1PresenterStandardProxy riseCas1PresenterStandardProxy;
	@Inject
	RiseCas1ClaimListPresenterPlaceProxy riseCas1ClaimListPresenterPlaceProxy;
	@Inject
	RiseCas1ClaimInfoPresenterPlaceProxy riseCas1ClaimInfoPresenterPlaceProxy;
	@Inject
	RisErrorPresenterPlaceProxy risErrorPresenterPlaceProx;

	@Inject
	public EagerProxies() {
	}
}

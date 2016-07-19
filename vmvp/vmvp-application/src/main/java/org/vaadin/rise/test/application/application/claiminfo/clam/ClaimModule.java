package org.vaadin.rise.test.application.application.claiminfo.clam;

import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoModule;

/**
 * Created by Aleksandr on 12.07.2016.
 */

@RiseModule(
        view = ClaimView.class,
		viewApi = Claim.View.class,
        presenter = ClaimPresenter.class,
		presenterApi = Claim.Presenter.class,
        parent = ClaimInfoModule.class
)
public class ClaimModule {
}

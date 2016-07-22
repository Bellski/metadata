package org.vaadin.rise.test.application.application.claiminfo;

import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.test.application.application.Cas1Entry;

/**
 * Created by Aleksandr on 12.07.2016.
 */

@RiseModule(
        view = ClaimInfoView.class,
		viewApi = ClaimInfo.View.class,
        presenter = ClaimInfoPresenter.class,
		presenterApi = ClaimInfo.Presenter.class,
        parent = Cas1Entry.class
)
public class ClaimInfoModule {
}

package org.vaadin.rise.test.application.application.claimlist;

import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.test.application.application.Cas1Entry;

/**
 * Created by Aleksandr on 12.07.2016.
 */

@RiseModule(
        view = ClaimListView.class,
		viewApi = ClaimList.View.class,
        presenter = ClaimListPresenter.class,
		presenterApi = ClaimList.Presenter.class,
        parent = Cas1Entry.class
)
public class ClaimListModule {
}

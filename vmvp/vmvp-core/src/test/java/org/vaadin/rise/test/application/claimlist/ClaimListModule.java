package org.vaadin.rise.test.application.claimlist;

import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.test.application.Cas1Application;

/**
 * Created by Aleksandr on 12.07.2016.
 */

@RiseModule(
        view = ClaimListView.class,
        presenter = ClaimListPresenter.class,
        parent = Cas1Application.class
)
public class ClaimListModule {
}

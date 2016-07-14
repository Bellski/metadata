package org.vaadin.rise.test.application.application.claiminfo;

import org.vaadin.rise.core.annotation.RiseModule;
import org.vaadin.rise.test.application.application.Cas1Application;

/**
 * Created by Aleksandr on 12.07.2016.
 */

@RiseModule(
        view = ClaimInfoView.class,
        presenter = ClaimInfoPresenter.class,
        parent = Cas1Application.class
)
public class ClaimInfoModule {
}

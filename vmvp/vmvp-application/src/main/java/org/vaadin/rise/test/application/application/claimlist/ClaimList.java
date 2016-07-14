package org.vaadin.rise.test.application.application.claimlist;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public interface ClaimList {
    interface View extends RiseView<Presenter> {

    }

    interface Presenter extends RisePresenter<View> {

    }
}

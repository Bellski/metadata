package org.vaadin.rise.test.application.claimlist;

import org.vaadin.rise.core.RiseViewImpl;
import org.vaadin.rise.test.application.Cas1Presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Singleton
public class ClaimListView extends RiseViewImpl implements ClaimList.View {


    @Inject
    public ClaimListView(Cas1Presenter.Slot1 slot1) {
        System.out.println("ClaimListView " + slot1);
    }
}

package org.vaadin.rise.test.application.application.claimlist;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.test.application.application.Cas1Presenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Presenter(
    placeName = "claimlist",
    defaultPlace = true,
    authorizePlace = true
)
public class ClaimListPresenter extends RisePresenterImpl<ClaimList.View> implements ClaimList.Presenter {

    @Inject
    protected ClaimListPresenter(ClaimList.View view, Cas1Presenter.Slot1 slot1) {
        super(view, slot1);
        System.out.println("ClaimListPresenter");
    }
}

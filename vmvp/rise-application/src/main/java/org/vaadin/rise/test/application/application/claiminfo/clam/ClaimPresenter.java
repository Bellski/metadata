package org.vaadin.rise.test.application.application.claiminfo.clam;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.place.PlaceRequest;
import org.vaadin.rise.test.application.UseDynamicProxy;
import org.vaadin.rise.test.application.application.claiminfo.ClaimInfoPresenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Presenter(placeName = "claim/{claimId}")
@UseDynamicProxy(proxy = ClaimDynamicProxyPlace.class)
public class ClaimPresenter extends RisePresenterImpl<Claim.View> implements Claim.Presenter {

    @Inject
    protected ClaimPresenter(Claim.View view, ClaimInfoPresenter.Slot2 slot2) {
        super(view, slot2);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
    }
}

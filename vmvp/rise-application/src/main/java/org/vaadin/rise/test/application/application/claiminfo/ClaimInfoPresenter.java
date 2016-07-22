package org.vaadin.rise.test.application.application.claiminfo;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.proxy.annotation.ThisIsNestedSlot;
import org.vaadin.rise.proxy.slot.IsNested;
import org.vaadin.rise.test.application.application.Cas1Presenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
@Presenter(placeName = "claiminfo")
public class ClaimInfoPresenter extends RisePresenterImpl<ClaimInfo.View> implements ClaimInfo.Presenter {

    @ThisIsNestedSlot
    public interface Slot2 extends IsNested<ClaimInfoPresenter> {

    }

    @Inject
    protected ClaimInfoPresenter(ClaimInfo.View view, Cas1Presenter.Slot1 slot1) {
        super(view, slot1);
    }
}

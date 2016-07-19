package org.vaadin.rise.test.application.application.claiminfo;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public class ClaimInfoView extends RiseViewImpl<ClaimInfo.Presenter> implements ClaimInfo.View {
    private final VerticalLayout vRoot = new VerticalLayout();
    private final VerticalLayout panel = new VerticalLayout();

    @Inject
    public ClaimInfoView(UI ui, ClaimInfoPresenter.Slot2 slot2) {
        super(ui);

        vRoot.addComponent(new Label("ClaimInfoView"));
        vRoot.addComponent(panel);

        initComponent(vRoot);

        bindSlot(slot2, panel);
    }
}

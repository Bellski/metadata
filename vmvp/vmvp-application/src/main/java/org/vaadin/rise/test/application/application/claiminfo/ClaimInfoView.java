package org.vaadin.rise.test.application.application.claiminfo;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public class ClaimInfoView extends RiseViewImpl<ClaimInfo.Presenter> implements ClaimInfo.View {

    @Inject
    public ClaimInfoView(UI ui) {
        super(ui);

        initComponent(new Label("ClaimInfoView"));
    }
}

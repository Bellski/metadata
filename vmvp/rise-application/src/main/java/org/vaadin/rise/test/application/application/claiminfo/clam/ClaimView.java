package org.vaadin.rise.test.application.application.claiminfo.clam;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public class ClaimView extends RiseViewImpl<Claim.Presenter> implements Claim.View {

    @Inject
    public ClaimView(UI ui) {
        super(ui);

        initComponent(new Label("ClaimView"));
    }
}

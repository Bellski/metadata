package org.vaadin.rise.test.application.application.claimlist;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public class ClaimListView extends RiseViewImpl<ClaimList.Presenter> implements ClaimList.View {

    @Inject
    public ClaimListView(UI ui) {
        super(ui);

        initComponent(new Label("ClaimnList"));
    }
}

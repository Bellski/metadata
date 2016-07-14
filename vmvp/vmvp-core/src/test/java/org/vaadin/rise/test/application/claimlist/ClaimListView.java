package org.vaadin.rise.test.application.claimlist;

import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public class ClaimListView extends RiseViewImpl<ClaimList.Presenter> implements ClaimList.View {

    @Inject
    public ClaimListView(UI ui) {
        super(ui);
    }
}

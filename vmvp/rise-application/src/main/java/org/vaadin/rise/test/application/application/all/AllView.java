package org.vaadin.rise.test.application.application.all;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class AllView extends RiseViewImpl<All.Presenter> implements All.View {

    @Inject
    public AllView(UI ui) {
        super(ui);

        initComponent(new Label("ALL"));
    }
}

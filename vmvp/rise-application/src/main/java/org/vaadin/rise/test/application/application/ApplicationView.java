package org.vaadin.rise.test.application.application;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class ApplicationView extends RiseViewImpl<Application.Presenter> implements Application.View {

    @Inject
    public ApplicationView(UI ui) {
        super(ui);

        initComponent(new Label("OLA"));
    }
}

package org.vaadin.rise.test.application.application.error;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 12.07.2016.
 */
public class ErrorView extends RiseViewImpl<Error.Presenter> implements Error.View {

    @Inject
    public ErrorView(UI ui) {
        super(ui);

        initComponent(new Label("Error"));
    }
}

package org.vaadin.rise.test.application.plugins.someplugin;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class SomePluginView extends RiseViewImpl<SomePlugin.Presenter> implements SomePlugin.View {

    @Inject
    public SomePluginView(UI ui) {
        super(ui);

        initComponent(new Label("SomePlugin"));
    }
}

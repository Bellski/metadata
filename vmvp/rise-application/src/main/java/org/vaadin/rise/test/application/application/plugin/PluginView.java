package org.vaadin.rise.test.application.application.plugin;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class PluginView extends RiseViewImpl<Plugin.Presenter> implements Plugin.View {

    @Inject
    public PluginView(UI ui, PluginPresenter.PluginSlot pluginSlot) {
        super(ui);

        final Panel components = new Panel();
        initComponent(components);

        bindSlot(pluginSlot, components);
    }
}

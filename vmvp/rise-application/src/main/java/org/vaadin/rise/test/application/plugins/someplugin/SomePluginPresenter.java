package org.vaadin.rise.test.application.plugins.someplugin;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.deprecated.proxy.slot.IsNested;
import org.vaadin.rise.place.deprecated.PlaceRequest;
import org.vaadin.rise.test.application.application.plugin.PluginPresenter;

import javax.inject.Inject;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class SomePluginPresenter extends RisePresenterImpl<SomePlugin.View> implements SomePlugin.Presenter  {

    @Inject
    protected SomePluginPresenter(SomePlugin.View view, PluginPresenter.PluginSlot pluginSlot) {
        super(view, pluginSlot);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
    }
}

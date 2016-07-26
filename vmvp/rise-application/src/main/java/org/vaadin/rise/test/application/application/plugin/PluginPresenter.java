package org.vaadin.rise.test.application.application.plugin;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.deprecated.proxy.slot.IsNested;
import org.vaadin.rise.deprecated.proxy.slot.NestedSlot;
import org.vaadin.rise.place.deprecated.PlaceRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aleksandr on 26.07.2016.
 */
public class PluginPresenter extends RisePresenterImpl<Plugin.View> implements Plugin.Presenter  {

    @Singleton
    public static class PluginSlot extends NestedSlot<PluginPresenter> implements IsNested<PluginPresenter> {

        @Inject
        public PluginSlot(Lazy<PluginPresenter> presenter) {
            super(presenter);
        }
    }

    @Inject
    protected PluginPresenter(Plugin.View view, RootPresenter.RootSlot rootSlot) {
        super(view, rootSlot);
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
    }
}

package org.vaadin.rise.core;

import com.vaadin.ui.UI;
import org.vaadin.rise.vaadin.IsComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/14/16.
 */

@Singleton
public class RootView extends RiseViewImpl<Root.Presenter> implements Root.View {
	private final RootPresenter.RootSlot rootSlot;

	@Inject
	public RootView(UI ui, RootPresenter.RootSlot rootSlot) {
		super(ui);
		this.rootSlot = rootSlot;
	}

	@Override
	public void removeFromSlot(Object slot, IsComponent content) {
		setInSlot(slot, null);
	}

	@Override
	public void setInSlot(Object slot, IsComponent content) {
		assert slot == rootSlot : "Unknown slot used in the root proxy.";

		getUI().setContent(content.asComponent());
	}
}

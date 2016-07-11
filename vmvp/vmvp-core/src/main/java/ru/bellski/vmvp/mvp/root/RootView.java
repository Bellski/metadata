package ru.bellski.vmvp.mvp.root;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import ru.bellski.vmvp.mvp.VMVPViewImpl;
import ru.bellski.vmvp.vaadin.IsComponent;

import javax.inject.Inject;

/**
 * Created by oem on 7/11/16.
 */
public class RootView extends VMVPViewImpl<Root.Presenter> implements Root.View {
	private final UI ui;

	@Inject
	public RootView(UI ui) {
		this.ui = ui;
	}

	@Override
	public Component asComponent() {
		assert false : "Root view has no widget, you should never call asWidget()";
		return null;
	}

	@Override
	public void removeFromSlot(Object slot, IsComponent content) {
		setInSlot(slot, null);
	}

	@Override
	public void setInSlot(Object slot, IsComponent content) {
		assert slot == RootPresenter.rootSlot : "Unknown slot used in the root proxy.";

		if (content != null) {
			ui.setContent(content.asComponent());
		}
	}

	@Override
	public void setUsingRootLayoutPanel(boolean usingRootLayout) {

	}
}

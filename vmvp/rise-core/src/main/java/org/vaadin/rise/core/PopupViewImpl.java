package org.vaadin.rise.core;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * Created by oem on 8/2/16.
 */
public class PopupViewImpl<PRESENTER extends RisePresenter<?>> extends RiseViewImpl<PRESENTER> implements PopupView<PRESENTER> {
	public PopupViewImpl(UI ui) {
		super(ui);
	}

	@Override
	protected void initComponent(Component component) {
		throw new UnsupportedOperationException("Use Window as component");
	}

	protected void initComponent(Window component) {
		super.initComponent(component);
	}

	@Override
	public void hide() {
		getUI().removeWindow(asComponent());
	}

	@Override
	public void show() {
		getUI().addWindow(asComponent());
	}

	@Override
	public Window asComponent() {
		return (Window) super.asComponent();
	}
}

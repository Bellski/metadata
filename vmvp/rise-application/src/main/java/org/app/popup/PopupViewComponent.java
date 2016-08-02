package org.app.popup;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.vaadin.rise.core.PopupViewImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 8/2/16.
 */
@Singleton
public class PopupViewComponent extends PopupViewImpl<PopUp.Presenter> implements PopUp.View {
	final Window window = new Window();

	@Inject
	public PopupViewComponent(UI ui) {
		super(ui);

		initComponent(window);

		window.center();

		window.setWidth("200px");
		window.setHeight("100px");

		window.setContent(new Label("POPUP"));
	}
}

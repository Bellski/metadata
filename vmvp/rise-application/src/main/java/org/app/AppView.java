package org.app;

import com.vaadin.ui.UI;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;

/**
 * Created by oem on 7/29/16.
 */
public class AppView extends RiseViewImpl<App.Presenter> implements App.View {

	@Inject
	public AppView(UI ui) {
		super(ui);
	}
}

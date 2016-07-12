package org.vaadin.rise.test.application.application.home;

import com.vaadin.ui.Label;
import org.vaadin.rise.test.application.mvp.VMVPViewImpl;

import javax.inject.Inject;

/**
 * Created by oem on 7/11/16.
 */
public class HomeView extends VMVPViewImpl<Home.Presenter> implements Home.View {

	@Inject
	public HomeView() {
		initComponent(new Label("home"));
	}
}

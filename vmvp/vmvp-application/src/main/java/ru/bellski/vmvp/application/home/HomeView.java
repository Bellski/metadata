package ru.bellski.vmvp.application.home;

import com.vaadin.ui.Label;
import ru.bellski.vmvp.mvp.VMVPViewImpl;

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

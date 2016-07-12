package org.vaadin.rise.test.application.application;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.rise.test.application.mvp.VMVPViewImpl;

import javax.inject.Inject;

/**
 * Created by oem on 7/11/16.
 */
public class ApplicationView extends VMVPViewImpl<Application.Presenter> implements Application.View {

	@Inject
	public ApplicationView() {
		VerticalLayout verticalLayout = new VerticalLayout();


		final TextField textField = new TextField();


		verticalLayout.addComponents(textField, new Button("Click", (Button.ClickListener) clickEvent -> {
			textField.setValue("Clicked");
		}));

		initComponent(verticalLayout);
	}
}

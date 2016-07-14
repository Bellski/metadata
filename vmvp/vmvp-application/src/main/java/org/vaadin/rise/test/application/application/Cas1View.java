package org.vaadin.rise.test.application.application;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.rise.core.RiseViewImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class Cas1View extends RiseViewImpl<Cas1.Presenter> implements Cas1.View {

	private final VerticalLayout vRoot = new VerticalLayout();
	private final Panel panel = new Panel();

	@Inject
	public Cas1View(UI ui, Cas1Presenter.Slot1 slot1) {
		super(ui);

		vRoot.addComponent(new Label("Cas1Application"));
		vRoot.addComponent(panel);

		initComponent(vRoot);

		bindSlot(slot1, panel);
	}
}

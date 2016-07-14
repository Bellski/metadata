package org.vaadin.rise.test.application;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import org.vaadin.rise.core.RiseViewImpl;
import org.vaadin.rise.test.application.mvp.slots.IsSingleSlot;
import org.vaadin.rise.test.application.vaadin.IsComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class Cas1View extends RiseViewImpl<Cas1.Presenter> implements Cas1.View {

	@Inject
	public Cas1View(Cas1Presenter.Slot1 slot1) {
		bindSlot((IsSingleSlot<?>) slot1, new Panel());
	}
}

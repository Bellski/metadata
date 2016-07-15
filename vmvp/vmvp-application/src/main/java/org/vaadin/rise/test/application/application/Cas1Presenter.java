package org.vaadin.rise.test.application.application;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.proxy.slot.IsNested;
import org.vaadin.rise.proxy.slot.IsSlot;

import javax.inject.Inject;

/**
 * Created by oem on 7/12/16.
 */

@Presenter
public class Cas1Presenter extends RisePresenterImpl<Cas1.View> implements Cas1.Presenter {

	public interface Slot1 extends IsNested<Cas1Presenter> {}

	@Inject
	protected Cas1Presenter(Cas1.View view, RootPresenter.RootSlot rootSlot) {
		super(view, rootSlot);
	}
}

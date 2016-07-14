package org.vaadin.rise.test.application;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RiseView;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.proxy.annotation.StandardProxy;
import org.vaadin.rise.proxy.slot.NestedSlot;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@StandardProxy
public class Cas1Presenter extends RisePresenterImpl<Cas1.View> implements Cas1.Presenter {

	public abstract static class Slot1 extends NestedSlot {}

	@Inject
	protected Cas1Presenter(Cas1.View view, RootPresenter.RootSlot rootSlot) {
		super(view, rootSlot);
	}
}

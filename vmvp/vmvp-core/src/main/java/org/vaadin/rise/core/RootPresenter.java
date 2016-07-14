package org.vaadin.rise.core;

import com.vaadin.ui.Component;
import dagger.Lazy;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.proxy.slot.NestedSlot;
import org.vaadin.rise.proxy.slot.SingleSlot;
import org.vaadin.rise.proxy.slot.event.RevealContentEvent;
import org.vaadin.rise.proxy.slot.event.RevealRootContentEvent;
import org.vaadin.rise.proxy.slot.event.RevealRootContentHandler;
import org.vaadin.rise.proxy.slot.handler.RevealContentHandler;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/14/16.
 */

@Singleton
public class RootPresenter extends RisePresenterImpl<Root.View> implements Root.Presenter {

	public abstract static class RootSlot extends NestedSlot {}


	@Singleton
	public static class RiseRootSlot extends RootSlot {
		private final Lazy<RootPresenter> cas1Presenter;

		@Inject
		public RiseRootSlot(Lazy<RootPresenter> cas1Presenter) {
			this.cas1Presenter = cas1Presenter;
		}

		@Override
		public <T extends RisePresenterComponent<?>> void setContent(T content) {
			final RootPresenter preseter = cas1Presenter.get();
			preseter.forceReveal();
			preseter.setInSlot(this, content);
		}
	}

	@Inject
	public RootPresenter(Root.View view) {
		super(view);
		visible = true;
	}

	@Override
	public Component asComponent() {
		assert false : "Root getView has no Component, you should never call asComponent()";
		return null;
	}

}

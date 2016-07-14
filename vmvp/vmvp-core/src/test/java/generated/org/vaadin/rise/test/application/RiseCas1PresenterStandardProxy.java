package generated.org.vaadin.rise.test.application;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.EventBus;
import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.event.RiseEventBus;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.proxy.slot.NestedSlot;
import org.vaadin.rise.proxy.slot.handler.RevealContentHandler;
import org.vaadin.rise.test.application.Cas1Presenter;
import org.vaadin.rise.proxy.slot.event.RevealContentEvent;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class RiseCas1PresenterStandardProxy implements Proxy<Cas1Presenter> {

	@Singleton
	public static class NestedSlot1 extends Cas1Presenter.Slot1 implements RevealContentHandler {

		private final Lazy<Cas1Presenter> cas1Presenter;
		private final RiseEventBus eventBus;

		@Inject
		public NestedSlot1(RiseEventBus eventBus, Lazy<Cas1Presenter> cas1Presenter) {
			this.cas1Presenter = cas1Presenter;
			this.eventBus = eventBus;
			eventBus.addHandler(this, this);
		}

		@Override
		public void onRevealContent(RevealContentEvent event) {
			final Cas1Presenter preseter = cas1Presenter.get();
			preseter.forceReveal();
			preseter.setInSlot(event.getAssociatedType(), event.getContent());
		}
	}

	private final Lazy<Cas1Presenter> cas1Presenter;
	private final RiseEventBus eventBus;

	@Inject
	public RiseCas1PresenterStandardProxy(RiseEventBus eventBus, Lazy<Cas1Presenter> cas1Presenter, NestedSlot1 nestedSlot1) {
		this.cas1Presenter = cas1Presenter;
		this.eventBus = eventBus;
	}

	@Override
	public void fireEvent(Event<?> event) {
		eventBus.fireEvent(event);
	}

	@Override
	public RiseEventBus getEventBus() {
		return eventBus;
	}

	@Override
	public Cas1Presenter getPresenter() {
		return cas1Presenter.get();
	}
}

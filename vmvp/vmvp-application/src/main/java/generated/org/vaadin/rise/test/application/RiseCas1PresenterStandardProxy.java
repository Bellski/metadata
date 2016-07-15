package generated.org.vaadin.rise.test.application;

import dagger.Lazy;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.proxy.Proxy;
import org.vaadin.rise.proxy.slot.NestedSlot;
import org.vaadin.rise.test.application.application.Cas1Presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by oem on 7/12/16.
 */
@Singleton
public class RiseCas1PresenterStandardProxy implements Proxy<Cas1Presenter> {

	static class NestedSlot1 extends NestedSlot<Cas1Presenter> implements Cas1Presenter.Slot1 {

		@Inject
		public NestedSlot1(Lazy<Cas1Presenter> presenter) {
			super(presenter);
		}
	}

	private final Lazy<Cas1Presenter> cas1Presenter;

	@Inject
	public RiseCas1PresenterStandardProxy(Lazy<Cas1Presenter> cas1Presenter) {
		this.cas1Presenter = cas1Presenter;
	}


	@Override
	public Cas1Presenter getPresenter() {
		return cas1Presenter.get();
	}
}

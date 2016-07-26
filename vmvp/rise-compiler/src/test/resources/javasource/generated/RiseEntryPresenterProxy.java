package javasource;

import javax.inject.Inject;

import org.vaadin.rise.deprecated.proxy.BaseProxy;
import org.vaadin.rise.deprecated.proxy.LazyPlacePresenter;

public class RiseEntryPresenterProxy extends BaseProxy<EntryPresenter> {

	@Inject
	public RiseEntryPresenterProxy(LazyPlacePresenter<EntryPresenter> lazyPresenter) {
		super(lazyPresenter);
	}
}
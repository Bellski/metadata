package javasource;

import javax.inject.Inject;

import javasource.EntryPresenter;

import org.vaadin.rise.proxy.BaseProxy;
import org.vaadin.rise.proxy.LazyPresenter;

public class RiseEntryPresenterProxy extends BaseProxy<EntryPresenter> {

	@Inject
	public RiseEntryPresenterProxy(LazyPresenter<EntryPresenter> lazyPresenter) {
		super(lazyPresenter);
	}
}
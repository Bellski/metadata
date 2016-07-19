package javasource;


import dagger.Lazy;
import javax.inject.Inject;

import javasource.EntryPresenter;

import org.vaadin.rise.proxy.BaseProxy;

public class RiseEntryPresenterProxy extends BaseProxy<EntryPresenter> {

	@Inject
	public RiseEntryPresenterProxy(Lazy<EntryPresenter> lazyPresenter) {
		super(lazyPresenter);
	}
}
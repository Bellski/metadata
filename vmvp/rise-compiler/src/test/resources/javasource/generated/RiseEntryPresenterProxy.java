package javasource;


import dagger.Lazy;
import org.vaadin.rise.proxy.BaseProxy;

import javax.inject.Inject;

public class RiseEntryPresenterProxy extends BaseProxy<EntryPresenter> {

	@Inject
	public RiseEntryPresenterProxy(Lazy<EntryPresenter> lazyPresenter) {
		super(lazyPresenter);
	}
}
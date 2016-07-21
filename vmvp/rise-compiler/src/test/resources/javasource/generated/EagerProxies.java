package javasource;

import javax.inject.Inject;
import javax.inject.Singleton;

import javasource.RiseSubEntryPresenterProxy;
import javasource.RiseEntryPresenterProxy;
import javasource.RiseSubSubEntryPresenterProxy;



@Singleton
public class EagerProxies {
	@Inject
	RiseSubSubEntryPresenterProxy RiseSubSubEntryPresenterProxy;
	@Inject
	RiseSubEntryPresenterProxy RiseSubEntryPresenterProxy;
	@Inject
	RiseEntryPresenterProxy RiseEntryPresenterProxy;

	@Inject
	public EagerProxies() {
	}
}

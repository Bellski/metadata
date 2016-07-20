package javasource;

import javax.inject.Inject;
import javax.inject.Singleton;



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

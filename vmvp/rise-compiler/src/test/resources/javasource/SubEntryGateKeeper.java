package javasource;

import org.vaadin.rise.security.Gatekeeper;
import org.vaadin.rise.security.GatekeeperWithParams;

public class SubEntryGateKeeper implements GatekeeperWithParams {

	@Override
	public GatekeeperWithParams withParams(String[] params) {
		return null;
	}

	@Override
	public boolean canReveal() {
		return false;
	}
}
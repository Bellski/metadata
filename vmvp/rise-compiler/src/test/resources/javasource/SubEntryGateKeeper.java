package javasource;

import org.vaadin.rise.place.CanReveal;
import org.vaadin.rise.security.GatekeeperWithParams;

public class SubEntryGateKeeper implements GatekeeperWithParams {

	@Override
	public GatekeeperWithParams withParams(String[] params) {
		return null;
	}

	@Override
	public CanReveal canReveal() {
		return new CanReveal(true, null);
	}
}
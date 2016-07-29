package javasource;

import org.vaadin.rise.place.CanReveal;
import org.vaadin.rise.security.Gatekeeper;
import org.vaadin.rise.security.annotation.DefaultGateKeeper;

@DefaultGateKeeper
public class DefaultEntryGatekeeper implements Gatekeeper {
	@Override
	public CanReveal canReveal() {
		return new CanReveal(true, null);
	}
}
package javasource;

import org.vaadin.rise.place.CanReveal;
import org.vaadin.rise.security.Gatekeeper;
import org.vaadin.rise.security.annotation.DefaultGateKeeper;

@DefaultGateKeeper
public class DefaultEntryGatekeeper implements Gatekeeper {
	@Override
	public boolean canReveal() {
		return true;
	}

	@Override
	public String fallBackNamePlace() {
		return null;
	}
}
package org.vaadin.rise.security;

/**
 * Created by oem on 7/21/16.
 */
public interface GatekeeperWithParams  extends Gatekeeper {
	GatekeeperWithParams withParams(String[] params);
}

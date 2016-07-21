package org.vaadin.rise.place;

import org.vaadin.rise.security.Gatekeeper;

public class PlaceWithGatekeeper extends PlaceImpl {
    private final Gatekeeper gatekeeper;

    public PlaceWithGatekeeper(
            String nameToken,
            Gatekeeper gatekeeper) {
        this(new String[] { nameToken }, gatekeeper);
    }

    public PlaceWithGatekeeper(
            String[] nameTokens,
            Gatekeeper gatekeeper) {
        super(nameTokens);

        this.gatekeeper = gatekeeper;
    }

    @Override
    public boolean canReveal() {
        return gatekeeper.canReveal();
    }
}
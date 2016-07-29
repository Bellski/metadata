package org.vaadin.rise.security;

import org.vaadin.rise.place.CanReveal;

public interface Gatekeeper {
    CanReveal canReveal();
}
package org.vaadin.rise.security;

public interface Gatekeeper {
    boolean canReveal();
    String fallBackNamePlace();
}
package org.vaadin.rise.place;

/**
 * Created by oem on 7/29/16.
 */
public class AuthorizeException extends Exception {
	private final String fallBack;

	public AuthorizeException(String fallBack) {
		this.fallBack = fallBack;
	}

	public String getFallBack() {
		return fallBack;
	}
}

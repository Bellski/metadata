package org.vaadin.rise.place;

/**
 * Created by oem on 7/29/16.
 */
public class CanReveal {
	private boolean canReveal;
	private String fallBackPlace;

	public CanReveal(boolean canReveal, String fallBackPlace) {
		this.canReveal = canReveal;
		this.fallBackPlace = fallBackPlace;
	}

	public boolean can() {
		return canReveal;
	}

	public String getFallBackPlace() {
		return fallBackPlace;
	}
}

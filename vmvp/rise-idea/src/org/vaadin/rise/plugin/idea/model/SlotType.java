package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/4/16.
 */
public enum SlotType {
	NESTED_SLOT("org.vaadin.rise.slot.NestedSlot", "NestedSlot"), POPUP_SLOT("org.vaadin.rise.slot.IsPopupSlot", "IsPopup");

	public final String name;
	public final String fqn;

	SlotType(String fqn, String name) {
		this.name = name;
		this.fqn = fqn;
	}

	public String getName() {
		return name;
	}

	public String getFqn() {
		return fqn;
	}

}

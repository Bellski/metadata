package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/3/16.
 */
public class RiseViewModel extends PsiClassModel{
	private String apiName;
	private boolean isPopup;

	public RiseViewModel(String packageName) {
		super(packageName);
	}

	@Override
	public void setName(String name) {
		super.setName(name.concat("View"));
		apiName = name;
	}

	public String getApiName() {
		return apiName;
	}

	public void setPopup(boolean popup) {
		isPopup = popup;
	}

	public boolean isPopup() {
		return isPopup;
	}

}

package org.vaadin.rise.codegen.model;

/**
 * Created by oem on 7/18/16.
 */
public class PlaceManagerModuleModel extends JFOModel {
	private String defaultPlaceNameToken;
	private String errorPlaceNameToken;
	private String unauthorizedPlaceNameToken;

	public PlaceManagerModuleModel(String packageName) {
		super("PlaceManagerModule", "PlaceManagerModule", packageName);
	}

	public void setDefaultPlaceNameToken(String defaultPlaceNameToken) {
		this.defaultPlaceNameToken = defaultPlaceNameToken;
	}

	public void setErrorPlaceNameToken(String errorPlaceNameToken) {
		this.errorPlaceNameToken = errorPlaceNameToken;
	}

	public void setUnauthorizedPlaceNameToken(String unauthorizedPlaceNameToken) {
		this.unauthorizedPlaceNameToken = unauthorizedPlaceNameToken;
	}

	public String getDefaultPlaceNameToken() {
		return defaultPlaceNameToken;
	}

	public String getErrorPlaceNameToken() {
		return errorPlaceNameToken;
	}

	public String getUnauthorizedPlaceNameToken() {
		return unauthorizedPlaceNameToken;
	}
}

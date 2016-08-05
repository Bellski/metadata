package org.vaadin.rise.codegen.light.model;

/**
 * Created by oem on 8/5/16.
 */
public class PlaceManagerModuleModel extends JFOModel {
	private String defaultPlace;
	private String errorPlace;
	private String unauthorizedPlace;

	private FqnHolder errorManager;
	private NameTokenPartsModel nameTokenPartsModel;

	public PlaceManagerModuleModel() {
		super("PlaceManagerModule");
	}

	public String getDefaultPlace() {
		return defaultPlace;
	}

	public void setDefaultPlace(String defaultPlace) {
		this.defaultPlace = defaultPlace;
	}

	public String getErrorPlace() {
		return errorPlace;
	}

	public void setErrorPlace(String errorPlace) {
		this.errorPlace = errorPlace;
	}

	public String getUnauthorizedPlace() {
		return unauthorizedPlace;
	}

	public void setUnauthorizedPlace(String unauthorizedPlace) {
		this.unauthorizedPlace = unauthorizedPlace;
	}


	public FqnHolder getErrorManager() {
		return errorManager;
	}

	public void setErrorManager(FqnHolder errorManager) {
		this.errorManager = errorManager;
		addImport(errorManager);
	}

	public NameTokenPartsModel getNameTokenPartsModel() {
		return nameTokenPartsModel;
	}

	public void setNameTokenPartsModel(NameTokenPartsModel nameTokenPartsModel) {
		this.nameTokenPartsModel = nameTokenPartsModel;
		addImport(nameTokenPartsModel);
	}
}

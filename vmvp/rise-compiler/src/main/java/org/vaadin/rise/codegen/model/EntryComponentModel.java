package org.vaadin.rise.codegen.model;

/**
 * Created by oem on 7/19/16.
 */
public class EntryComponentModel extends JFOModel {
	private ModuleModel entryModuleModel;

	public EntryComponentModel(ModuleModel entryModuleModel) {
		super(entryModuleModel.getClassName() + "Component", entryModuleModel.getClassName() + "Component", entryModuleModel.getPackageName());
		addImport(getPackageName() + ".RiseBootstrapModule");
		addImport(getPackageName() + ".Bootstrap");

		this.entryModuleModel = entryModuleModel;
		addImport(entryModuleModel);
	}

	public ModuleModel getEntryModuleModel() {
		return entryModuleModel;
	}
}

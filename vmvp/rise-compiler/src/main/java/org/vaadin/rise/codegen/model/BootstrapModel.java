package org.vaadin.rise.codegen.model;

/**
 * Created by oem on 7/19/16.
 */
public class BootstrapModel extends JFOModel {
	private final EagerProxiesModel eagerProxiesModel;

	public BootstrapModel(EagerProxiesModel eagerProxiesModel) {
		super("Bootstrap", "Bootstrap", eagerProxiesModel.getPackageName());
		this.eagerProxiesModel = eagerProxiesModel;
	}

	public EagerProxiesModel getEagerProxiesModel() {
		return eagerProxiesModel;
	}
}

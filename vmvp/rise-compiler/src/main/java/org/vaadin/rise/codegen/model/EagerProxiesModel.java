package org.vaadin.rise.codegen.model;

import java.util.Collection;

/**
 * Created by oem on 7/18/16.
 */
public class EagerProxiesModel extends JFOModel {
	private final Collection<PlaceModel> proxyModels;

	public EagerProxiesModel(Collection<PlaceModel> proxyModels, String packageName) {
		super("EagerProxies", "EagerProxies", packageName);
		this.proxyModels = proxyModels;

		proxyModels.forEach(this::addImport);
	}

	public Collection<PlaceModel> getProxyModels() {
		return proxyModels;
	}
}

package org.vaadin.rise.codegen.model;

/**
 * Created by oem on 7/19/16.
 */
public class ProxyModel extends JFOModel {
	private final FqnHolder proxyTarget;
	private String placeName;

	public ProxyModel(String className, String packageName, FqnHolder proxyTarget) {
		super("Rise" + className + "Proxy", "Rise" + className + "Proxy", packageName);
		this.proxyTarget = proxyTarget;
		addImport(proxyTarget);
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public FqnHolder getProxyTarget() {
		return proxyTarget;
	}

	public boolean isProxyPlace() {
		return placeName != null;
	}
}

package org.vaadin.rise.codegen.model;

/**
 * Created by oem on 7/19/16.
 */
public class PlaceModel extends JFOModel {
	private final FqnHolder proxyTarget;
	private String placeName;
	private FqnHolder gateKeeper;

	public PlaceModel(String className, String packageName, FqnHolder proxyTarget) {
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

	public void setGateKeeper(FqnHolder gateKeeper) {
		this.gateKeeper = gateKeeper;
		addImport(gateKeeper);
	}

	public FqnHolder getGateKeeper() {
		return gateKeeper;
	}

	public boolean hasGateKeeper() {
		return gateKeeper != null;
	}
}

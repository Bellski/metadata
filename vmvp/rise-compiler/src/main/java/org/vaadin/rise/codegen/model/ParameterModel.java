package org.vaadin.rise.codegen.model;

/**
 * Created by oem on 7/21/16.
 */
public class ParameterModel {
	private final String name;
	private final FqnHolder fqnHolder;

	public ParameterModel(String name, FqnHolder fqnHolder) {

		this.name = name;
		this.fqnHolder = fqnHolder;
	}

	public String getName() {
		return name;
	}

	public FqnHolder getFqnHolder() {
		return fqnHolder;
	}
}

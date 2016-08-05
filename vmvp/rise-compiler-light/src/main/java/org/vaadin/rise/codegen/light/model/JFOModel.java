package org.vaadin.rise.codegen.light.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by oem on 7/18/16.
 */
public class JFOModel extends FqnHolder {
	private final Set<String> importList = new HashSet<>();

	public JFOModel(String className, String packageName) {
		super(className, packageName);
	}

	public JFOModel(String className) {
		super(className);
	}

	public JFOModel() {
		super();
	}

	protected void addImport(String fqn) {
		importList.add(fqn);
	}

	protected void addImport(FqnHolder jfoModel) {
		addImport(jfoModel.getFullyQualifiedName());
	}

	public Set<String> getImportList() {
		return importList;
	}


	@Override
	public String toString() {
		return getFullyQualifiedName();
	}
}

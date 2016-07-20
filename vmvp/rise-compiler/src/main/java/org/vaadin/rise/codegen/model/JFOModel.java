package org.vaadin.rise.codegen.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by oem on 7/18/16.
 */
public class JFOModel extends FqnHolder {
	private final Set<String> importList = new HashSet<>();

	public JFOModel(String className, String fullClassName, String packageName) {
		super(className, fullClassName, packageName);
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

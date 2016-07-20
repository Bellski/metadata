package org.vaadin.rise.codegen.model;

import com.sun.tools.javac.code.Symbol;

import javax.lang.model.element.Element;

/**
 * Created by oem on 7/18/16.
 */
public class FqnHolder {
	private final String className;
	private final String fullClassName;
	private final String packageName;

	public FqnHolder(String className, String fullClassName, String packageName) {
		this.className = className;
		this.fullClassName = fullClassName;
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public String getFullClassName() {
		return fullClassName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getFullyQualifiedName() {
		return packageName + "." + fullClassName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FqnHolder fqnHolder = (FqnHolder) o;

		if (!className.equals(fqnHolder.className)) return false;
		return packageName.equals(fqnHolder.packageName);

	}

	@Override
	public int hashCode() {
		int result = className.hashCode();
		result = 31 * result + packageName.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return getFullyQualifiedName();
	}

	public static FqnHolder buildJavaCompatibleFQN(Element element) {
		final Symbol symbolElement = Symbol.class.cast(element);
		final String fqn = symbolElement.getQualifiedName().toString();
		final String packageName = symbolElement.packge().toString();
		final String fullName = fqn.substring(packageName.length() + 1);

		return new FqnHolder(fullName.replaceAll("\\.", ""), fullName, packageName);
	}
}

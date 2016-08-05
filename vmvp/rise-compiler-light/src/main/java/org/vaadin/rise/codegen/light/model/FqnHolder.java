package org.vaadin.rise.codegen.light.model;

import com.sun.tools.javac.code.Symbol;

import javax.lang.model.element.Element;

/**
 * Created by oem on 7/18/16.
 */
public class FqnHolder {
	private String className;
	private String packageName;

	public FqnHolder(String className, String packageName) {
		this.className = className;
		this.packageName = packageName;
	}

	public FqnHolder(String className) {
		this.className = className;
	}

	public FqnHolder() {
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getFullyQualifiedName() {
		return packageName + "." + className;
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

		return new FqnHolder(symbolElement.getSimpleName().toString(), packageName);
	}
}

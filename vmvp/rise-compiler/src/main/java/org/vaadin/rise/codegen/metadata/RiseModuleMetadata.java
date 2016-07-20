package org.vaadin.rise.codegen.metadata;

import com.sun.tools.javac.code.Symbol;

import javax.lang.model.element.Element;

/**
 * Created by oem on 7/15/16.
 */
public class RiseModuleMetadata implements RiseModuleMetadataApi {
	private final String className;
	private final String riseFqn;
	private final Element element;
	private boolean isEntry = false;

	private Element viewElement;
	private Element presenterElement;

	public RiseModuleMetadata(Element element) {
		this(element, false);
	}

	public RiseModuleMetadata(Element element, boolean isEntry) {
		this.className = element.getSimpleName().toString();

		this.riseFqn = Symbol.class.cast(element).packge() + ".Rise" + className;
		this.element = element;
		this.isEntry = isEntry;
	}

	@Override
	public String getClassName() {
		return className;
	}

	public Element getElement() {
		return element;
	}

	public boolean isEntry() {
		return isEntry;
	}

	public void setEntry(boolean entry) {
		isEntry = entry;
	}

	public String getRiseFqn() {
		return riseFqn;
	}

	public Element getViewElement() {
		return viewElement;
	}

	public void setViewElement(Element viewElement) {
		this.viewElement = viewElement;
	}

	public Element getPresenterElement() {
		return presenterElement;
	}

	public void setPresenterElement(Element presenterElement) {
		this.presenterElement = presenterElement;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RiseModuleMetadata that = (RiseModuleMetadata) o;

		return className.equals(that.className);

	}

	@Override
	public int hashCode() {
		return className.hashCode();
	}

	@Override
	public String toString() {
		return className;
	}
}

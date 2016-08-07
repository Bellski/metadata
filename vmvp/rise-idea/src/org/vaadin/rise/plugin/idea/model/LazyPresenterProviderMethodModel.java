package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/4/16.
 */
public class LazyPresenterProviderMethodModel {
	private final PsiClassModel presenterImpl;
	private final PsiClassModel slot;

	public LazyPresenterProviderMethodModel(PsiClassModel presenterImpl, PsiClassModel slot) {
		this.presenterImpl = presenterImpl;
		this.slot = slot;
	}

	public PsiClassModel getPresenterImpl() {
		return presenterImpl;
	}

	public PsiClassModel getSlot() {
		return slot;
	}
}

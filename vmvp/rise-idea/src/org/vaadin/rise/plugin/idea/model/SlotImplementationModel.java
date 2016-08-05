package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/4/16.
 */
public class SlotImplementationModel extends PsiClassModel {
	private final PsiClassModel implementsSlot;
	private final PsiClassModel presenterImpl;
	private SlotType slotType;

	public SlotImplementationModel(String name, PsiClassModel implementsSlot, PsiClassModel presenterImpl, SlotType slotType) {
		super(name, "");
		this.implementsSlot = implementsSlot;
		this.presenterImpl = presenterImpl;
		this.slotType = slotType;
	}

	public PsiClassModel getImplementsSlot() {
		return implementsSlot;
	}

	public PsiClassModel getPresenterImpl() {
		return presenterImpl;
	}

	public SlotType getSlotType() {
		return slotType;
	}
}

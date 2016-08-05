package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/3/16.
 */
public class RisePresenterModel extends PsiClassModel {
	private PresenterType presenterType;
	private String apiName;
	private SlotItem slot;
	private String nameToken;

	public RisePresenterModel(String packageName) {
		super(packageName);
	}

	public void setPresenterType(PresenterType presenterType) {
		this.presenterType = presenterType;
	}

	@Override
	public void setName(String name) {
		super.setName(name.concat("Presenter"));
		apiName = name;
	}

	public PresenterType getPresenterType() {
		return presenterType;
	}

	public String getApiName() {
		return apiName;
	}

	public SlotItem getSlot() {
		return slot;
	}

	public void setSlot(SlotItem slot) {
		this.slot = slot;
	}

	public String getNameToken() {
		return nameToken;
	}

	public void setNameToken(String nameToken) {
		if (nameToken.isEmpty()) {
			nameToken = null;
		}
		this.nameToken = nameToken;
	}

	public boolean isPresenterImpl() {
		return presenterType == PresenterType.NESTED_PRESENTER || presenterType == PresenterType.PLACE_PRESENTER;
	}
}

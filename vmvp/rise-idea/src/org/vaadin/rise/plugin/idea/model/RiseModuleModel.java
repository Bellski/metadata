package org.vaadin.rise.plugin.idea.model;

/**
 * Created by oem on 8/4/16.
 */
public class RiseModuleModel extends PsiClassModel {
	private final AppInterfaceModel apiInterfaceModel;
	private final RiseViewModel riseViewModel;
	private final RisePresenterModel presenterModel;
	private SlotItem slotItem;

	public RiseModuleModel(String packageName) {
		super(packageName);
		this.apiInterfaceModel = new AppInterfaceModel(packageName);
		this.riseViewModel = new RiseViewModel(packageName);
		this.presenterModel = new RisePresenterModel(packageName);
	}

	@Override
	public void setName(String name) {
		super.setName(name.concat("DaggerModule"));
		apiInterfaceModel.setName(name);
		riseViewModel.setName(name);
		presenterModel.setName(name);
	}

	public AppInterfaceModel getApiInterfaceModel() {
		return apiInterfaceModel;
	}

	public RiseViewModel getRiseViewModel() {
		return riseViewModel;
	}

	public RisePresenterModel getPresenterModel() {
		return presenterModel;
	}

	public void setSlotItem(SlotItem slotItem) {
		this.slotItem = slotItem;
		presenterModel.setSlot(slotItem);
	}

	public SlotItem getSlotItem() {
		return slotItem;
	}
}

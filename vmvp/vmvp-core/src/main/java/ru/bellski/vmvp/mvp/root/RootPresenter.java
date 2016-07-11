package ru.bellski.vmvp.mvp.root;

import ru.bellski.vmvp.mvp.VMVPPresenterComponent;
import ru.bellski.vmvp.mvp.VMVPPresenterImpl;
import ru.bellski.vmvp.proxy.VMVPEventBus;
import ru.bellski.vmvp.proxy.events.RevealRootContentEvent;

import javax.inject.Inject;

/**
 * Created by oem on 7/11/16.
 */
public class RootPresenter extends VMVPPresenterComponent<Root.View> implements Root.Presenter, RevealRootContentHandler {

	public static final SingleSlot<VMVPPresenterImpl<?,?>> rootSlot = new SingleSlot<>();

	@Inject
	public RootPresenter(Root.View view, VMVPEventBus eventBus) {
		super(view, eventBus);
		visible = true;
	}

	@Override
	public void onRevealRootContent(RevealRootContentEvent event) {
		getView().setUsingRootLayoutPanel(false);
		setInSlot(rootSlot, event.getContent());
	}
}

package org.vaadin.rise.core;

import com.vaadin.ui.Component;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.slot.NestedSlot;

import javax.inject.Inject;

/**
 * Created by oem on 7/14/16.
 */
@Presenter
public class RootPresenter extends RisePresenterImpl<Root.View> implements Root.Presenter {

	public static final NestedSlot ROOT_SLOT = new NestedSlot();

	@Inject
	public RootPresenter(Root.View view) {
		super(view);
		visible = true;
	}

	@Override
	public Component asComponent() {
		assert false : "Root getView has no Component, you should never call asComponent()";
		return null;
	}
}

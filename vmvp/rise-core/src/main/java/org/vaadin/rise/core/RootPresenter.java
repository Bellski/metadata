package org.vaadin.rise.core;

import com.vaadin.ui.Component;
import dagger.Lazy;
import org.vaadin.rise.core.annotation.Presenter;
import org.vaadin.rise.deprecated.proxy.slot.IsNested;
import org.vaadin.rise.deprecated.proxy.slot.NestedSlot;

import javax.inject.Inject;

/**
 * Created by oem on 7/14/16.
 */
@Presenter
public class RootPresenter extends RisePresenterImpl<Root.View> implements Root.Presenter {

	public interface RootSlot extends IsNested<RootPresenter> {}

	public static class RiseRootSlot extends NestedSlot<RootPresenter> implements RootSlot {
		@Inject
		public RiseRootSlot(Lazy<RootPresenter> presenter) {
			super(presenter);
		}
	}

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

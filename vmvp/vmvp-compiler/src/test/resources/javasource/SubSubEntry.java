package javasource;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;

public interface SubSubEntry {
	interface View extends RiseView<Presenter> {

	}

	interface Presenter extends RisePresenter<View> {

	}
}
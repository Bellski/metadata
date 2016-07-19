package javasource;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;
import org.vaadin.rise.core.annotation.RiseModule;

/**
 * Created by oem on 7/15/16.
 */
@RiseModule(
	view = SubSubEntryView.class,
	viewApi = SubSubEntry.View.class,
	presenter = SubSubEntryPresenter.class,
	presenterApi = SubSubEntry.Presenter.class,
	parent = EntryModule.class
)
public class SubSubEntryModule {
}

package javasource;

import org.vaadin.rise.core.annotation.RiseModule;

/**
 * Created by oem on 7/15/16.
 */
@RiseModule(
	view = SubEntryView.class,
	viewApi = SubEntry.View.class,
	presenter = SubEntryPresenter.class,
	presenterApi = SubEntry.Presenter.class,
	parent = EntryModule.class
)
public class SubEntryModule {
}

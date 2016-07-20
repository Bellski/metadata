package javasource;

import org.vaadin.rise.annotation.ApplicationEntry;
import org.vaadin.rise.core.annotation.RiseModule;

/**
 * Created by oem on 7/15/16.
 */
@ApplicationEntry
@RiseModule(
	view = EntryView.class,
	viewApi = Entry.View.class,
	presenter = EntryPresenter.class,
	presenterApi = Entry.Presenter.class
)
public class EntryModule {
}

package javasource;

import org.vaadin.rise.core.annotation.RiseModule;

/**
 * Created by oem on 7/15/16.
 */
@RiseModule(
	view = SubEntryView.class,
	presenter = SubEntryPresenter.class,
	parent = EntryModule.class
)
public class SubEntryModule {
}

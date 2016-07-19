package javasource;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RiseView;
import org.vaadin.rise.core.RootPresenter;
import org.vaadin.rise.core.annotation.Presenter;

import javax.inject.Inject;

/**
 * Created by oem on 7/15/16.
 */
@Presenter(
	placeName = "subsub"
)
public class SubSubEntryPresenter extends RisePresenterImpl<SubSubEntry.View> implements SubSubEntry.Presenter {

	@Inject
	protected SubSubEntryPresenter(SubSubEntry.View view) {
		super(view);
	}
}

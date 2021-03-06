package javasource;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.annotation.Presenter;

import javax.inject.Inject;

/**
 * Created by oem on 7/15/16.
 */
@Presenter(
	placeName = "!/sub",
	useGateKeeper = SubEntryGateKeeper.class,
	defaultPlace = true,
	errorPlace = true
)
public class SubEntryPresenter extends RisePresenterImpl<SubEntry.View> implements SubEntry.Presenter {

	@Inject
	protected SubEntryPresenter(SubEntry.View view) {
		super(view);
	}
}

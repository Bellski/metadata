package	codegen.presenters;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.core.RiseView;
import org.vaadin.rise.core.annotation.Presenter;

import javax.inject.Inject;

@Presenter(placeName = "!/home")
public class UserPresenter extends RisePresenterImpl<RiseView<?>> implements RisePresenter<RiseView<?>> {

	@Inject
	public UserPresenter(RiseView view) {
		super(view);
	}
}
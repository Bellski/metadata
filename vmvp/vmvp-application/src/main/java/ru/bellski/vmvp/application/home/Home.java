package ru.bellski.vmvp.application.home;

import ru.bellski.vmvp.mvp.VMVPPresenter;
import ru.bellski.vmvp.mvp.VMVPView;

/**
 * Created by oem on 7/11/16.
 */
public interface Home {
	interface View extends VMVPView<Presenter> {

	}

	interface Presenter extends VMVPPresenter<View> {

	}

}
